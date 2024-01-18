public class Board {

    private static Board board;
    private Square[][] squares = new Square[6][7];

    private Board() {

        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 7; j++) {
                squares[i][j] = new Square(i, j);
            }
        }

        // set initial Piece position for blue
        squares[0][0].setPiece(new PlusPiece(false));
        squares[0][1].setPiece(new HourglassPiece(false));
        squares[0][2].setPiece(new TimePiece(false));
        squares[0][3].setPiece(new SunPiece(false));
        squares[0][4].setPiece(new TimePiece(false));
        squares[0][5].setPiece(new HourglassPiece(false));
        squares[0][6].setPiece(new PlusPiece(false));
        squares[1][0].setPiece(new PointPiece(false));
        squares[1][1].setPiece(new PointPiece(false));
        squares[1][2].setPiece(new PointPiece(false));
        squares[1][3].setPiece(new PointPiece(false));
        squares[1][4].setPiece(new PointPiece(false));
        squares[1][5].setPiece(new PointPiece(false));
        squares[1][6].setPiece(new PointPiece(false));

        // set initial Piece position for Yellow
        squares[5][0].setPiece(new PlusPiece(true));
        squares[5][1].setPiece(new HourglassPiece(true));
        squares[5][2].setPiece(new TimePiece(true));
        squares[5][3].setPiece(new SunPiece(true));
        squares[5][4].setPiece(new TimePiece(true));
        squares[5][5].setPiece(new HourglassPiece(true));
        squares[5][6].setPiece(new PlusPiece(true));
        squares[4][0].setPiece(new PointPiece(true));
        squares[4][1].setPiece(new PointPiece(true));
        squares[4][2].setPiece(new PointPiece(true));
        squares[4][3].setPiece(new PointPiece(true));
        squares[4][4].setPiece(new PointPiece(true));
        squares[4][5].setPiece(new PointPiece(true));
        squares[4][6].setPiece(new PointPiece(true));
    }

    public void resetBoard() {
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 7; j++) {
                squares[i][j].removePiece();
            }
        }

        // set initial Piece position for blue
        squares[0][0].setPiece(new PlusPiece(false));
        squares[0][1].setPiece(new HourglassPiece(false));
        squares[0][2].setPiece(new TimePiece(false));
        squares[0][3].setPiece(new SunPiece(false));
        squares[0][4].setPiece(new TimePiece(false));
        squares[0][5].setPiece(new HourglassPiece(false));
        squares[0][6].setPiece(new PlusPiece(false));
        squares[1][0].setPiece(new PointPiece(false));
        squares[1][1].setPiece(new PointPiece(false));
        squares[1][2].setPiece(new PointPiece(false));
        squares[1][3].setPiece(new PointPiece(false));
        squares[1][4].setPiece(new PointPiece(false));
        squares[1][5].setPiece(new PointPiece(false));
        squares[1][6].setPiece(new PointPiece(false));

        // set initial Piece position for Yellow
        squares[5][0].setPiece(new PlusPiece(true));
        squares[5][1].setPiece(new HourglassPiece(true));
        squares[5][2].setPiece(new TimePiece(true));
        squares[5][3].setPiece(new SunPiece(true));
        squares[5][4].setPiece(new TimePiece(true));
        squares[5][5].setPiece(new HourglassPiece(true));
        squares[5][6].setPiece(new PlusPiece(true));
        squares[4][0].setPiece(new PointPiece(true));
        squares[4][1].setPiece(new PointPiece(true));
        squares[4][2].setPiece(new PointPiece(true));
        squares[4][3].setPiece(new PointPiece(true));
        squares[4][4].setPiece(new PointPiece(true));
        squares[4][5].setPiece(new PointPiece(true));
        squares[4][6].setPiece(new PointPiece(true));
    }

    public void changeBehaviourTimeAndPlus() {
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 7; j++) {
                if(squares[i][j].getPiece() != null && squares[i][j].getPiece() instanceof TimePiece) {
                    Piece currPiece = squares[i][j].getPiece();
                    ((TimePiece)currPiece).changeBehaviour();
                } else if(squares[i][j].getPiece() != null && squares[i][j].getPiece() instanceof PlusPiece) {
                    Piece currPiece = squares[i][j].getPiece();
                    ((PlusPiece)currPiece).changeBehaviour();
                }
            }
        }
    }

    public static Board getBoard() {
        if(board == null) board = new Board();
        return board;
    }

    public Square[][] getSquares() {
        return squares;
    }

}
