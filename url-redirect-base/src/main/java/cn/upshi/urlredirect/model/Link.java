package cn.upshi.urlredirect.model;

/**
 * url-redirect cn.upshi.urlredirect.model
 * 描述：
 * 时间：2017-2-5 13:41.
 */

public class Link {

    private String uuid;
    private String name;
    private String code;
    private String url;

    public Link() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Link{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
