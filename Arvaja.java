public class Arvaja {
    private String arvajaNimi;
    private int arvamisKord;
    private int punktiskoor;

    public Arvaja(String arvajaNimi, int arvamisKord, int punktiskoor) {
        this.arvajaNimi = arvajaNimi;
        this.arvamisKord = arvamisKord;
        this.punktiskoor = punktiskoor;
    }

    public String getArvajaNimi() {
        return arvajaNimi;
    }

    public int getArvamisKord() {
        return arvamisKord;
    }

    public void setArvamisKord(int arvamisKord) {
        this.arvamisKord = arvamisKord;
    }

    public int getPunktiskoor() {
        return punktiskoor;
    }

    public void setPunktiskoor(int punktiskoor) {
        this.punktiskoor = punktiskoor;
    }

    @Override
    public String toString() {
        return "Arvaja nimega " + arvajaNimi +
                ", kelle hetke punktiskoor on " + punktiskoor + ".";
    }
    //Kuskil peaks toimuma punktide arvutamine vaheseisuks ja ka lõppseisuks
    //mida rohkem on korduvalt üritatud vastata, seda vähem punkte jagatakse
    public void LisaPunktiskoor(){
        this.punktiskoor += 1;
    }
    public void VõtaPunktMaha(){
        this.punktiskoor -= 1;
    }

}
