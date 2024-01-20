pluginYamlPath=$pluginYamlPath
pluginPath=$pluginPath

echo "插件信息：$pluginYamlPath"
echo "插件路径：$pluginPath"

java -jar ./plugin-generator-jar-with-dependencies.jar \
    $pluginYamlPath \
    $pluginPath
