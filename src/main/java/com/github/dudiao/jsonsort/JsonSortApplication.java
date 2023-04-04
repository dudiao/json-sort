package com.github.dudiao.jsonsort;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportRuntimeHints;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@SpringBootApplication
@ImportRuntimeHints(MyNativeHintRegistrar.class)
public class JsonSortApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(JsonSortApplication.class, args);
  }

  @Value("${json.input}")
  private File jsonFile;

  @Value("${json.output}")
  private File outputDir;

  @Override
  public void run(String... args) throws Exception {
    // 读取 jsonFile 文件，并按照 remark_name 分组，将每组的数据，生成到不同的json文件中，文件名为 {remark_name}.json
    // 例如：remark_name 为 "A"，则生成 A.json 文件
    ObjectMapper objectMapper = new ObjectMapper();
    List<User> users = objectMapper.readValue(jsonFile, new TypeReference<List<User>>() {
    });
    if (!outputDir.exists()) {
      outputDir.mkdirs();
    }
    log.info("导出的文件路径：{}", outputDir.getAbsolutePath());
    for (Map.Entry<String, List<User>> entry : users.stream().collect(Collectors.groupingBy(User::getRemarkName)).entrySet()) {
      String k = entry.getKey();
      List<User> v = entry.getValue();
      File jsonFile = new File("%s/%s.json".formatted(outputDir.getAbsolutePath(), k));

      boolean newFile = jsonFile.createNewFile();
      if (!newFile) {
        log.warn("file {} already exists", jsonFile.getAbsolutePath());
      } else {
        log.info("create file {}", jsonFile.getAbsolutePath());
      }

      try {
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(v);
        FileWriter fileWriter = new FileWriter(jsonFile);
        fileWriter.write(json);
        fileWriter.close();
      } catch (Exception e) {
        log.error("write json file error", e);
      }
    }

  }
}
