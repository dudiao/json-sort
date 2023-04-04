解压`json-sort-windows-latest.zip`得到`target/json-sort.exe`

在`json-sort.exe`目录下，打开`cmd`（黑窗口）

使用：
```
json-sort.exe --json.input=<json文件全路径> --json.output=<输出的json文件夹>
```
`json.output`参数可以不指定，默认输出到`json-sort.exe`所在目录的`target/json/`

比如：
``` 
json-sort.exe --json.input=D:\\json\\friend.json
```