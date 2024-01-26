public class TalabiaChess {
    
    public static void main(String[] args) {

        TalabiaView theView = new TalabiaView();
        TalabiaModel theModel = new TalabiaModel();
        TalabiaController theController = new TalabiaController(theView, theModel);
        theView.setSize(700, 700);
        theView.setVisible(true);
        
    }
}
