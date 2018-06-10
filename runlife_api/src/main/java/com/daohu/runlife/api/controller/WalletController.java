package com.daohu.runlife.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.daohu.runlife.api.constant.SysConfig;
import com.daohu.runlife.api.domain.entity.ExchangeStep;
import com.daohu.runlife.api.domain.model.StepEntity;
import com.daohu.runlife.api.domain.entity.Wallet;
import com.daohu.runlife.api.domain.model.JsonResult;
import com.daohu.runlife.api.ethereum.TokenERC_sol_TokenERC20;
import com.daohu.runlife.api.ethereum.TokenERC_sol_tokenRecipient;
import com.daohu.runlife.api.repository.ExchangeStepMapper;
import com.daohu.runlife.api.service.ExchangeStepService;
import com.daohu.runlife.api.service.WalletService;
import com.daohu.runlife.api.utis.EncryptionUtil;
import com.daohu.runlife.api.utis.HttpUtil;
import com.daohu.runlife.api.utis.WxRunEncodeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.codehaus.plexus.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.text.DecimalFormat;

@RestController
@RequestMapping("/wallet")
public class WalletController {
    private final static String WX_MINI_APP_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
    private final static String contractAddress = "0x3370496666e97968ddd901b31200853a169d70d1";
    @Autowired
    private WalletService walletService;

    @Autowired
    private ExchangeStepService exchangeStepService;

    @GetMapping("/deployed-sol")
    public JsonResult<String> deployedSol(){
        JsonResult<String> ret = new JsonResult<>();
        try {
//            Web3j web3 = Web3j.build(new HttpService());  // defaults to http://localhost:8545/
//            Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().send();
//            EthBlockNumber blockNum = web3.ethBlockNumber().send();
//            BigInteger num = blockNum.getBlockNumber();
//            ret = web3ClientVersion.getWeb3ClientVersion();
//            ret += " blocknum:"+num;
//            EthGetBalance balance = web3.ethGetBalance("0x56034c6b36cee982d5e917882196c0e2afac6266", DefaultBlockParameterName.LATEST).send();
//            ret += " balance:"+balance.getBalance();
//            web3.shutdown();
            // 创建一个 web3j 的连接
            Web3j web3j = Web3j.build(new HttpService());

            // 部署的时候需要用到该账户的 gas，务必保证该账户余额充足
            Credentials credentials = WalletUtils.loadCredentials(
                    "test",
                    "wallet/UTC--2018-05-17T07-21-02.590147500Z--321a5335c99a84b250c703a1c21582fc2d90c318");
            //查询余额
            EthGetBalance balance = web3j.ethGetBalance("0x321a5335c99A84B250c703a1C21582fc2D90C318", DefaultBlockParameterName.LATEST).send();
            System.out.println("部署合约的账户余额："+balance.getBalance());
//            System.out.println("私钥："+credentials.getEcKeyPair().getPrivateKey());//私钥
            // 部署合约
            Uint256 totalSupply = new Uint256(14*10000000);
            Utf8String tokenName = new Utf8String("Step");
            Utf8String tokenSymbol = new Utf8String("step");
            TokenERC_sol_TokenERC20 tokenERC_sol_tokenERC20
                    = TokenERC_sol_TokenERC20.deploy(web3j, credentials, BigInteger.valueOf(200000), BigInteger.valueOf(20000000),
                    totalSupply,tokenName,tokenSymbol).send();
            // 部署完成后打印合约地址
            System.out.println("部署的合约地址："+tokenERC_sol_tokenERC20.getContractAddress());
            ret.setData(tokenERC_sol_tokenERC20.getContractAddress());
            web3j.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
            ret.setMsg(e.getMessage());
        }catch (CipherException ex){
            ex.printStackTrace();
            ret.setMsg(ex.getMessage());
        }catch (Exception ex){
            ex.printStackTrace();
            ret.setMsg(ex.getMessage());
        }
        return ret;
    }
    @GetMapping("/get-balance")
    public JsonResult<String> getBalance(@Param("address") String address){
        JsonResult ret = new JsonResult();
        if(StringUtils.isEmpty(address) || !WalletUtils.isValidAddress(address)){
            ret.setMsg("address is invalid");
        }else{
            Web3j web3j = null;
            try {
                Credentials credentials = WalletUtils.loadCredentials(
                        "huqiangtest",
                        "wallet/UTC--2018-05-17T07-21-02.590147500Z--321a5335c99a84b250c703a1c21582fc2d90c318");

                web3j = Web3j.build(new HttpService());  // defaults to http://localhost:8545/
                TokenERC_sol_TokenERC20 tokenERC_sol_tokenERC20 = TokenERC_sol_TokenERC20.load(
                        contractAddress,
                        web3j,
                        credentials,
                        BigInteger.valueOf(200000),
                        BigInteger.valueOf(20000000));
                //验证智能合约是否可用
                if(tokenERC_sol_tokenERC20.isValid()){
                    // 调用是能合约函数
                    Address toAddress = new Address(address);
                    BigInteger balance = tokenERC_sol_tokenERC20.balanceOf(toAddress).send().getValue();
                    //将balance转换成6位小数
                    DecimalFormat df=new DecimalFormat("0.000000");
                    String retBalance = df.format(Long.parseLong(String.valueOf(balance))/1000000f);
                    ret.setData(retBalance);
                    System.out.println("账户余额："+retBalance);
                }else{
                    //智能合约不可用
                    ret.setMsg("智能合约不可用");
                }
//                Web3j web3 = Web3j.build(new HttpService());  // defaults to http://localhost:8545/
//                EthGetBalance balance = web3.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
//                ret.setData(String.valueOf(balance.getBalance()));
                web3j.shutdown();
            } catch (IOException e) {
                e.printStackTrace();
                ret.setMsg(e.getMessage());
            }catch (CipherException ex){
                ex.printStackTrace();
            }catch (Exception ex){
                ex.printStackTrace();
            }
            finally {
                if(web3j != null){
                    web3j.shutdown();
                }
            }
        }
        return ret;
    }
    @RequestMapping("/exchange-step")
    public JsonResult<String> exchangeStep(@RequestParam("step") int step, @RequestParam("address") String address){
        JsonResult<String> ret = new JsonResult<>();
        if(StringUtils.isEmpty(address) || step <= 0){
            ret.setCode(100);
            ret.setMsg("address is invalid or step can't be 0");
        }else{
            //先判断是否今天已经兑换
            ExchangeStep exchangeStep = exchangeStepService.getCurrentDateRecordByAddress(address);
            if(exchangeStep != null){
                ret.setCode(100);
                ret.setMsg("今天已经兑换过了，请明天再来");
            }else{
                try {
                    //调用智能合约，合约内自动计算应该兑换多少币，然后再返回余额
                    // 填入刚才部署后打印出来的合约地址
                    Credentials credentials = WalletUtils.loadCredentials(
                            "huqiangtest",
                            "wallet/UTC--2018-05-17T07-21-02.590147500Z--321a5335c99a84b250c703a1c21582fc2d90c318");

                    Web3j web3j = Web3j.build(new HttpService());  // defaults to http://localhost:8545/
                    TokenERC_sol_TokenERC20 tokenERC_sol_tokenERC20 = TokenERC_sol_TokenERC20.load(
                            contractAddress,
                            web3j,
                            credentials,
                            BigInteger.valueOf(200000),
                            BigInteger.valueOf(20000000));
                    //验证智能合约是否可用
                    if(tokenERC_sol_tokenERC20.isValid()){
                        //调用智能合约
                        // 调用是能合约函数
                        Uint256 stepUint256 = new Uint256(step);
                        Address toAddress = new Address(address);
                        TransactionReceipt transactionReceipt = tokenERC_sol_tokenERC20.exchangeStepCoin(toAddress, stepUint256).send();
                        transactionReceipt.getBlockHash();
                        System.out.println("交易哈希："+transactionReceipt.getBlockHash());//返回hash说明正在等待确认了
                    }else{
                        //智能合约不可用
                        ret.setMsg("智能合约不可用");
                    }
                    EthGetBalance balance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
                    ret.setData(String.valueOf(balance.getBalance()));
                    web3j.shutdown();
                    exchangeStepService.storeExchangeRecord(address,step,0);
                } catch (IOException e) {
                    e.printStackTrace();
                    ret.setCode(101);
                    ret.setMsg("以太坊节点异常"+e.getMessage());
                }catch (CipherException ex){
                    ex.printStackTrace();
                    ret.setCode(101);
                    ret.setMsg(ex.getMessage());
                }catch (Exception ex){
                    ex.printStackTrace();
                    ret.setCode(101);
                    ret.setMsg(ex.getMessage());
                }
            }

        }
        return ret;
    }
    @RequestMapping("/create-address")
    public JsonResult<String> getAddress(@RequestParam("wxid") String wxid){
        JsonResult ret = new JsonResult();
        if(StringUtils.isEmpty(wxid)){
            ret.setMsg("params is invalid");
        }else{
            Wallet wallet = walletService.getWalletByWxid(wxid);
            if(wallet != null && !StringUtils.isEmpty(wallet.getAddress())){ //已经存在
                ret.setData(wallet);
            }else{
                try {
//                String filePath = "F://EthWallet/";
                    String filePath = "../springboot/wallet/";
                    String fileName;
                    //eth-密码需要自己管理，自己设置哦！
                    String password = EncryptionUtil.getHash(SysConfig.walletSalt + wxid ,"MD5");
                    fileName = WalletUtils.generateNewWalletFile(password, new File(filePath), false);
                    System.out.println(password);//密码
                    System.out.println(fileName);//保存你的加密文件信息
                    Credentials ALICE = WalletUtils.loadCredentials(password, filePath+fileName);
//                    System.out.println(ALICE.getAddress());//钱包地址
//                    System.out.println(ALICE.getEcKeyPair().getPrivateKey());//私钥
//                    System.out.println(ALICE.getEcKeyPair().getPublicKey());//公钥
                    //写入db
                    String json = FileUtils.fileRead(filePath+fileName);
                    Wallet tempWallet = new Wallet();
                    tempWallet.setWxid(wxid);
                    tempWallet.setWalletJson(json);
                    System.out.println("json="+json);
                    tempWallet.setAddress(ALICE.getAddress());
                    System.out.println(ALICE.getAddress());
                    walletService.storeWallet(tempWallet);
                    ret.setData(tempWallet);
                } catch (IOException e) {
                    e.printStackTrace();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }
        return ret;
    }
//    @RequestMapping("/exist")
//    public JsonResult<String> isExist(@RequestParam("wxid") String wxid){
//        JsonResult ret = new JsonResult();
//        if(StringUtils.isEmpty(wxid)){
//            ret.setMsg("params is invalid");
//        }else{
//            //先查询是否已有账号
//            Wallet wallet = walletService.getWalletByWxid(wxid);
//            if(wallet != null && !StringUtils.isEmpty(wallet.getAddress())){ //已经存在
//                ret.setData(true);
//            }else{
//                ret.setData(false);
//            }
//        }
//        return ret;
//    }
    @RequestMapping("/login")
    public JsonResult<String> login(@RequestParam("code") String wx_code){
        JsonResult ret = new JsonResult();
        if(StringUtils.isEmpty(wx_code)){
            ret.setMsg("params is invalid");
        }else{
            //访问微信服务器获取openid等数据
            String requestUrl = WX_MINI_APP_URL.replace("APPID", SysConfig.wxMiniAppid).replace("SECRET", SysConfig.wxMiniAppSecret)
                    .replace("JSCODE", wx_code);
            JSONObject jsonObject = HttpUtil.httpsRequest(requestUrl, "GET", null);
            System.out.println("登陆返回jsonObject="+jsonObject);
            String openId = jsonObject.getString("openid");
            String sessionKey = jsonObject.getString("session_key");
            if(StringUtils.isEmpty(openId)){
                ret.setMsg("code 已过期");
            }else{
                //再查看该id是否已经注册过钱包
                Wallet wallet = walletService.getWalletByWxid(openId);
                if(wallet != null && !StringUtils.isEmpty(wallet.getAddress())){ //已经存在
                    wallet.setSessionKey(sessionKey);
                    //请求区块链上的账户余额
                    JsonResult<String> json = getBalance(wallet.getAddress());
                    wallet.setBalance(json.getData());
                    ret.setData(wallet);
                }else{
                    Wallet wallet1 = new Wallet();
                    wallet1.setSessionKey(sessionKey);
                    wallet1.setWxid(openId);
                    ret.setData(wallet1);
                }
            }
        }
        return ret;
    }
    @RequestMapping("/get-rundata")
    public JsonResult<String> login(@RequestParam("sessionKey") String sessionKey,@RequestParam("encryptedData") String encryptedData,
                                    @RequestParam("iv") String iv){
        JsonResult ret = new JsonResult();
        try {
            String jsonStr = WxRunEncodeUtils.AES128CBCdecrypt(encryptedData,iv,SysConfig.wxMiniAppid,sessionKey);
            JSONObject json = JSONObject.parseObject(jsonStr);
            int size = json.getJSONArray("stepInfoList").size();
            JSONObject lastData = json.getJSONArray("stepInfoList").getJSONObject(size-1);
            int step = lastData.getInteger("step");
            float stepCoin = step/5112f;
            StepEntity stepEntity = new StepEntity();
            stepEntity.setStep(step);
            stepEntity.setAboutStep(stepCoin);
            ret.setData(stepEntity);
        } catch (Exception e) {
            e.printStackTrace();
            ret.setMsg(e.getMessage());
        }
        return ret;
    }
}
