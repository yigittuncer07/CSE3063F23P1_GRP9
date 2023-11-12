import java.text.DecimalFormat;

public class Grade {
    private double outOfHundred;
    private double gano;
    private String letterGrade;

    public Grade() {
    }

    public Grade(double outOfHundred, double gano, String letterGrade) {
        this.outOfHundred = outOfHundred;
        this.gano = gano;
        this.letterGrade = letterGrade;
    }

    public double getOutOfHundred() {
        return outOfHundred;
    }

    public void setOutOfHundred(double outOfHundred) {
        this.outOfHundred = outOfHundred;
    }

    public double getGano() {
        return gano;
    }

    public void setGano(double gano) {
        this.gano = gano;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }

    public double convertHundredToGano(double hundredNot) {
        if (hundredNot < 0 || hundredNot > 100) {
            throw new IllegalArgumentException("Grade must be between 0 and 100");
        }
        double result = hundredNot / 25;

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedResult = decimalFormat.format(result);

        return Double.parseDouble(formattedResult);
    }

    public String convertHundredToLetterGrade(double hundred) {
        if (hundred < 0 || hundred > 100) {
            throw new IllegalArgumentException("Grade must be between 0 and 100");
        }

        hundred = convertHundredToGano(hundred);

        if (4.00 >= hundred && hundred > 3.50) {
            return "AA";
        } else if (3.50 >= hundred && hundred > 3.00) {
            return "BA";
        } else if (3.00 >= hundred && hundred > 2.50) {
            return "BB";
        } else if (2.50 >= hundred && hundred > 2.00) {
            return "CB";
        } else if (2.00 >= hundred && hundred > 1.50) {
            return "CC";
        } else if (1.50 >= hundred && hundred > 1.00) {
            return "DC";
        } else if (1.00 >= hundred && hundred > 0.50) {
            return "DD";
        } else if (0.50 >= hundred && hundred > 0.00) {
            return "FD";
        } else {
            return "FF";
        }
    }

}
