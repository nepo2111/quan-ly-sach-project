
package model;

/**
 *
 * @author Admin
 */
public class ChuDe {
    private String maChuDe;
    private String tenChuDe;

    public ChuDe() {
    }

    public ChuDe(String maChuDe, String tenChuDe) {
        this.maChuDe = maChuDe;
        this.tenChuDe = tenChuDe;
    }
    
    public ChuDe(String maChuDe) {
        this.maChuDe = maChuDe;
    }
    public String getMaChuDe() {
        return maChuDe;
    }

    public void setMaChuDe(String maChuDe) {
        this.maChuDe = maChuDe;
    }

    public String getTenChuDe() {
        return tenChuDe;
    }

    public void setTenChuDe(String tenChuDe) {
        this.tenChuDe = tenChuDe;
    }

    
}
