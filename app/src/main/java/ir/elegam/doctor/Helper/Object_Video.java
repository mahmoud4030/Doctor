package ir.elegam.doctor.Helper;

public class Object_Video {

    public  String category_id;
    public String Oid;
    public String OThumb_url;
    public String OVideo_url;
    public String OText;

    public Object_Video(String category_id,String Oid, String OThumb_url, String OVideo_url, String OText){
        this.Oid = Oid;
        this.category_id=category_id;
        this.OThumb_url = OThumb_url;
        this.OVideo_url = OVideo_url;
        this.OText = OText;
    }


    public String getCategory_id(){return this.category_id;}

    public String getOid() {
        return Oid;
    }

    public void setOid(String oid) {
        Oid = oid;
    }

    public String getOThumb_url() {
        return OThumb_url;
    }

    public void setOThumb_url(String OThumb_url) {
        this.OThumb_url = OThumb_url;
    }

    public String getOVideo_url() {
        return OVideo_url;
    }

    public void setOVideo_url(String OVideo_url) {
        this.OVideo_url = OVideo_url;
    }

    public String getOText() {
        return OText;
    }

    public void setOText(String OText) {
        this.OText = OText;
    }
}// end class
