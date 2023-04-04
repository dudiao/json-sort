package com.github.dudiao.jsonsort;

import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

/**
 * @author songyinyin
 * @since 2023/4/2 17:28
 */
public class MyNativeHintRegistrar implements RuntimeHintsRegistrar {
  @Override
  public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
    hints.serialization().registerType(User.class);
//    hints.resources().registerPattern("/Users/songyinyin/study/json-sort/target/json/*.json");
  }
}
