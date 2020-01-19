public class Matrix {


    public double[][] addition (double[][] m1, double[][] m2){

        double[][] m3;

        if (m1.length == m2.length &&  m1[0].length == m2[0].length){
            m3 = new double[m1.length][m1[0].length];
            for (int i = 0; i<m1.length; i++){
                for (int j = 0; j<m1[0].length; j++){
                    m3[i][j] = (m1[i][j] + m2[i][j]);
                }
            }

        } else {
            throw new RuntimeException("Macierze mają różne wymiary");
        }

        return m3;
    }


    public double[][] subtraction (double[][] m1, double[][] m2){
        double[][] m3;

        if (m1.length == m2.length &&  m1[0].length == m2[0].length){
            m3 = new double[m1.length][m1[0].length];
            for (int i = 0; i<m1.length; i++){
                for (int j = 0; j<m1[0].length; j++){
                    m3[i][j] = (m1[i][j] - m2[i][j]);
                }
            }

        } else {
            throw new RuntimeException("Macierze mają różne wymiary");
        }

        return m3;
    }


    public double[][] scalar (double[][] m1, double sca){
        double [][] m3;
        m3 = new double [m1.length][m1[0].length];
        for(int i = 0; i < m3.length; i++){
            for (int j = 0; j < m3[0].length; j++){
                m3[i][j] = (m1[i][j] * sca);
            }
        }
        return m3;
    }


    public double[][] multiplication (double[][] m1, double[][] m2){
        double[][] m3;

        if (m1[0].length == m2.length){
            m3 = new double[m1.length][m2[0].length];
            for (int i = 0; i<m1.length; i++) {
                for (int j = 0; j < m1[0].length; j++) {
                    double temp = 0;
                    for (int k = 0; k < m2.length; k++) {
                        temp += (m1[i][k] * m2[k][j]);
                    }
                    m3[i][j] = temp;
                }
            }
        } else {
            throw new RuntimeException("Macierze mają niewłaściwe wymiary");
        }

        return m3;
    }

    public double[][] exponentiation(double[][] m1, int expon){
        if (expon == 0){
            double[][] m3 = new double[m1.length][m1[0].length];
            for (int i = 0; i < m3.length; i++){
                for (int j = 0; j < m3[0].length; j++){
                    if (i == j){
                        m3[i][j] = 1;
                    }
                    else{
                        m3[i][j] = 0;
                    }

                }
            }
            return m3;
        } else if (expon % 2 != 0){
            return this.multiplication(m1, this.exponentiation(m1, expon - 1));
        } else {
            return this.multiplication(m1,m1);
        }
    }

    public double[][] transposition (double[][] m1){

        double[][] m3 = new double[m1.length][m1[0].length];
        for (int i = 0; i < m1.length; i++){
            for(int j = 0; j < m1[0].length; j++){
                m3[j][i] = m1 [i][j];
            }
        }
        return m3;
    }

    public double determinant(double[][] m1) {

        double det = 0;

        if (m1.length == 1 && m1[0].length == 1) {
            det = m1[0][0];
        } else if (m1.length != m1[0].length) {
            throw new RuntimeException("Macierz nie jest kwadratowa");
        } else if (m1.length == 2 && m1[0].length == 2) {
            det = (m1[0][0] * m1[1][1] - m1[0][1] * m1[1][0]);
        } else {
            double[][] m3 = new double[m1.length + (m1.length - 1)][m1[0].length];
            for (int i = 0, _i = 0; i < m3.length; i++, _i++) {
                for (int j = 0; j < m1[0].length; j++) {
                    if (_i < m1.length && j < m1[0].length) {
                        m3[i][j] = m1[_i][j];
                    } else {
                        _i = 0;
                        m3[i][j] = m1[_i][j];
                    }
                }
            }

            double product = 1;
            int _i;

            for (int i = 0; i < m1.length; i++) {
                _i = i;
                for (int j = 0; j < m1[0].length; j++) {
                    product *= m3[_i][j];
                    _i++;
                }
                det += product;
                product = 1;
            }

            product = 1;
            for (int i = 0; i < m1.length; i++) {
                _i = i;
                for (int j = m1[0].length - 1; j >= 0; j--) {
                    product *= m3[_i][j];
                    _i++;
                }
                det -= product;
                product = 1;
            }
        }
        return det;
    }
}

