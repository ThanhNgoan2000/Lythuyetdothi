package maTranTrongSo;

public class Lable {
int L;
int P;
public Lable(int l, int p) {
	super();
	L = l;
	P = p;
}
public int getL() {
	return L;
}
public void setL(int l) {
	L = l;
}
public int getP() {
	return P;
}
public void setP(int p) {
	P = p;
}
@Override
public String toString() {
	return "Lable [L=" + L + ", P=" + P + "]";
}


}
