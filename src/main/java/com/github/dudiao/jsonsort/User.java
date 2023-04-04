package com.github.dudiao.jsonsort;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author songyinyin
 * @since 2023/4/2 16:26
 */
@Data
public class User implements Serializable {

  @JsonProperty("head_img")
  private String headImg;

  @JsonProperty("nick_name")
  private String nickName;

  @JsonProperty("remark_name")
  private String remarkName;

  @JsonProperty("user_name")
  private String userName;

  private String wxid;

}
