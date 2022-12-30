FROM centos:8
MAINTAINER  "hqj13691297331@163.com"
ENV JAVA_PATH=/usr/java/jdk-17
WORKDIR /usr/local/java
RUN rpm -ivh https://download.oracle.com/java/17/latest/jdk-17_linux-x64_bin.rpm && echo export JAVA_HOME=$JAVA_PATH >> /etc/profile && echo export PATH=\$PATH:\$JAVA_HOME/bin >> /etc/profile && source /etc/profile
# 配置环境变量
RUN java --version && rm -f /usr/local/java/jdk-17_linux-x64_bin.rpm
