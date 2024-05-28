public class Entry {
    private String website;
    private String username;
    private String password;
    private String url;
    private String time;

    public Entry (String website, String username, String password, String url, String time){
        this.website = website;
        this.username = username;
        this.password = password;
        this.url = url;
        this.time= time;
    }

   public String getTime(){
        return time;
   }
   public void setTime(String time){
        this.time = time;
   }
    public String getUsername (){
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String toString()
    {
        return website + "," + username + "," + password + "," + url + "," + time;
    }

}
