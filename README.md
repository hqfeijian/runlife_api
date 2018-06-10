# runlife_api

#### 项目介绍
区块链+计步运动项目，主要采用以太坊、智能合约、springboot以及小程序等技术

#### 软件架构

接口：springboot框架

区块链：以太坊私有链

承载端：小程序（小程序比较简单，只需要按照传统模式请求api即可）

智能合约编译工具：solc 、web3j Cammond Line

合约所在的目录：runlife_api\src\main\java\com\daohu\runlife\api\ethereum\TokenERC.sol

通过以下命令生成的文件为：TokenERC_sol_TokenERC20.java、TokenERC_sol_tokenRecipient.java

solc编译智能合约：

    solcjs TokenERC.sol --bin --abi --optimize -o ./

根据智能合约生成java代码

    web3j solidity generate --solidityTypes TokenERC_sol_TokenERC20.bin TokenERC_sol_TokenERC20.abi -o ./ -p com.daohu.runlife.api.ethereum

服务器api接口必须结合以太坊私有链一起

接口api定义：

[runlife接口定义](https://www.showdoc.cc/web/#/85918951788200)


#### 工具安装教程

1. solc安装

    npm install -g solc

2. web3j安装教程

见：[web3j 命令行的使用](https://www.jianshu.com/p/fbb92d745435)

3. 以太坊私有链环境搭建

见：[centos7.0中搭建自己的以太坊私链测试环境（一）](https://www.jianshu.com/p/52332fa4a24c)

#### 服务器启动说明

1. 启动以太坊私有链

    ./geth --datadir "./chain" --rpc --rpcapi "db,eth,net,web3,personal,admin,miner" --nodiscover console 2>>eth_output.log
2. 启动挖矿

    miner.start(1)

3. 启动服务器springboot

    nohup java -jar runlife-api-0.0.1-SNAPSHOT.jar >outlog.txt 2>&1 &

#### 隐私设置

请注意本代码库中我已经人为的把数据库mysql密码、部署合约的钱包密码、生成私钥的salt等等去除掉了，大家在使用代码的时候留意。


