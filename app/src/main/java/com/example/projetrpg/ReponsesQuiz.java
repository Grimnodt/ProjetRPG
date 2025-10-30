package com.example.projetrpg;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public class ReponsesQuiz implements Parcelable {

    public static final String KEY = "SAC_DE_REPONSES";

    private int reponseQ1;
    private int reponseQ2;
    private int reponseQ3;
    private ArrayList<Integer> reponseQ4;
    private boolean reponseMajeur;
    private String reponseQ5;
    private int reponseQ7;
    private int reponseQ8;
    private boolean reponseQ9;
    private int reponseQ10;
    private int reponseQ11;

    public ReponsesQuiz() {
        reponseQ1 = -1;
        reponseQ2 = -1;
        reponseQ3 = -1;
        reponseQ4 = new ArrayList<>();
        reponseMajeur = false;
        reponseQ5 = "";
        reponseQ7 = -1;
        reponseQ8 = -1;
        reponseQ9 = false;
        reponseQ10 = -1;
        reponseQ11 = -1;
    }

    protected ReponsesQuiz(Parcel in) {
        reponseQ1 = in.readInt();
        reponseQ2 = in.readInt();
        reponseQ3 = in.readInt();

        reponseQ4 = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            in.readList(reponseQ4, Integer.class.getClassLoader(), Integer.class);
        } else {
            in.readList(reponseQ4, Integer.class.getClassLoader());
        }

        reponseMajeur = in.readByte() != 0;
        reponseQ5 = in.readString();
        reponseQ7 = in.readInt();
        reponseQ8 = in.readInt();
        reponseQ9 = in.readByte() != 0;
        reponseQ10 = in.readInt();
        reponseQ11 = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(reponseQ1);
        dest.writeInt(reponseQ2);
        dest.writeInt(reponseQ3);
        dest.writeList(reponseQ4);
        dest.writeByte((byte) (reponseMajeur ? 1 : 0));
        dest.writeString(reponseQ5);
        dest.writeInt(reponseQ7);
        dest.writeInt(reponseQ8);
        dest.writeByte((byte) (reponseQ9 ? 1 : 0));
        dest.writeInt(reponseQ10);
        dest.writeInt(reponseQ11);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ReponsesQuiz> CREATOR = new Creator<ReponsesQuiz>() {
        @Override
        public ReponsesQuiz createFromParcel(Parcel in) {
            return new ReponsesQuiz(in);
        }

        @Override
        public ReponsesQuiz[] newArray(int size) {
            return new ReponsesQuiz[size];
        }
    };

    public int getReponseQ1() { return reponseQ1; }
    public void setReponseQ1(int reponseQ1) { this.reponseQ1 = reponseQ1; }

    public int getReponseQ2() { return reponseQ2; }
    public void setReponseQ2(int reponseQ2) { this.reponseQ2 = reponseQ2; }

    public int getReponseQ3() { return reponseQ3; }
    public void setReponseQ3(int reponseQ3) { this.reponseQ3 = reponseQ3; }

    public ArrayList<Integer> getReponseQ4() { return reponseQ4; }
    public void setReponseQ4(ArrayList<Integer> reponseQ4) { this.reponseQ4 = reponseQ4; }

    public boolean isReponseMajeur() { return reponseMajeur; }
    public void setReponseMajeur(boolean reponseMajeur) { this.reponseMajeur = reponseMajeur; }

    public String getReponseQ5() { return reponseQ5; }
    public void setReponseQ5(String reponseQ5) { this.reponseQ5 = reponseQ5; }

    public int getReponseQ7() { return reponseQ7; }
    public void setReponseQ7(int reponseQ7) { this.reponseQ7 = reponseQ7; }

    public int getReponseQ8() { return reponseQ8; }
    public void setReponseQ8(int reponseQ8) { this.reponseQ8 = reponseQ8; }

    public boolean isReponseQ9() { return reponseQ9; }
    public void setReponseQ9(boolean reponseQ9) { this.reponseQ9 = reponseQ9; }

    public int getReponseQ10() { return reponseQ10; }
    public void setReponseQ10(int reponseQ10) { this.reponseQ10 = reponseQ10; }

    public int getReponseQ11() { return reponseQ11; }
    public void setReponseQ11(int reponseQ11) { this.reponseQ11 = reponseQ11; }
}