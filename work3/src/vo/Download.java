package vo;

public class Download {
    private int id;
    private String name;
    private String icon;
    private String info;
    private String time;
    private String size;
    private int star;
    private String path;

    public Download() {
    }

    public Download(int id, String name, String icon, String info, String time, String size, int star, String path) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.info = info;
        this.time = time;
        this.size = size;
        this.star = star;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
