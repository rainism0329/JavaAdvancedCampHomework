package com.example.rpcfxcore.api;

import lombok.Data;

@Data
public class RpcfxRequest {
  private String serviceClass;
  private String method;
  private Object[] params;
}
