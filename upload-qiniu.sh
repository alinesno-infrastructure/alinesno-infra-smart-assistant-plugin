# jdk11
# export JAVA_HOME=
# export JRE_HOME=

if [ ! -f "./alinesno-upload-qiniu-tools.jar" ];then
  version=1.0
  wget -O alinenso-upload-qiniu-tools.jar http://data.linesno.com/tools/alinenso-upload-qiniu-tools-${version}.jar
else
  echo "文件[alinesno-upload-qiniu-tools.jar]已经存在"
fi

accessKey=$accessKey
secretKey=$secretKey
spaceBucket=$spaceBucket
domain=l8i1sx6m

localFolder=$1
remoteFolder=$remoteFolder
overrideUpload=true
refresh=true

echo "执行的文件名：$1"

java -jar ./alinenso-upload-qiniu-tools.jar \
    $accessKey \
    $secretKey \
    $spaceBucket \
    l8i1sx6m \
    $localFolder \
    $remoteFolder \
    $overrideUpload \
    $refresh
