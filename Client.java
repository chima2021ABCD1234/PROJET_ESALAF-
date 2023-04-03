package ma.fst.model;


// java beans (Entity)
public class Client {
    private int id_client;
    private String nom;
    private String prenom;
    private int credit;
    private String telepehone ;

    public int getId() {
        return id_client;
    }
    public void setId(int id_client) {
        this.id_client = id_client;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String gettelepehone() {
        return telepehone ;
    }

    public void settelepehone (String telepehone ) {
        this.telepehone  = telepehone ;
    }


    @Override
    public String toString() {
        return "Client{" +
                "id_client=" + id_client + "," +
                " nom='" + nom + '\'' +
                ", telepehone='" + telepehone + '\'' +
                ", prenom='" + prenom + '\'' +
                "Credit=" + credit + "," +
                '}';
    }
}






