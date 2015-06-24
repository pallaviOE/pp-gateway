package hello;

/**
 * Created by pallavi on 6/22/15.
 */
public class APIInfo {
  private String apiAccessKeyId;

  public String getApiSecretAccessKey() {
    return apiSecretAccessKey;
  }

  public void setApiSecretAccessKey(String apiSecretAccessKey) {
    this.apiSecretAccessKey = apiSecretAccessKey;
  }

  private String apiSecretAccessKey;

  public String getApiAccessKeyId() {
    return apiAccessKeyId;
  }

  public void setApiAccessKeyId(String apiAccessKeyId) {
    this.apiAccessKeyId = apiAccessKeyId;
  }
}
