public class Coordinate{
	public static final int MAX_COL = 3;
	public static final int MAX_ROW = 3;
	private int r;
	private int c;

	public Coordinate(int r, int c){
		this.r = r;
		this.c = c;
	}

	public int getRow(){
		return r;
	}

	public int getCol(){
		return c;
	}

	public boolean isCorner(){
		if(
			(r == 0 && c == 0) ||
			(r == 0 && c == MAX_COL) ||
			(r == MAX_ROW && c == 0) ||
			(r == MAX_ROW && c == MAX_COL)

		){
			return true;		
		} else {
			return false;
		}
	}

	public boolean isSide(){
		if(r == 0 || r == MAX_ROW || c == 0 || c == MAX_COL){
			return true;		
		} else {
			return false;
		}
	}

	public boolean isCentral(){
		if(isCorner() == false && isSide() == false){
			return true;
		} else {
			return false;
		}
	}

	public String toString(){
		return "r = " + r
		+ " , c = " + c;
	}


	// int arr[][] = {
	// 	 		{1,2,3,4},
	// 	 		{5,6,7,8},
	// 	 		{9,10,11,12},
	// 	 		{13,14,15,16}
	// 	 	};

	// public void swapButton(){
	// 	arr[3][3] = arr[r][c] + arr[3][3];
	// 	arr[r][c] = arr[3][3] - arr[r][c];
	// 	arr[3][3] = arr[3][3] - arr[r][c];
	// }

	// // public void getCoordsCorner(){
	// 	if(arr[r][c] == [0][0]){
	// 		System.out.println("" + arr[0][1]);
	// 		System.out.println("" + arr[1][0]);
	// 	}
	// 	if(arr[0][1] == arr[3][3] || arr[1][0] == arr[3][3]){
	// 		swapButton();
	// 	}
	// }

	// // public void getCoordsCorner()





	// // // public int getCoordsMid(){
	// // // 	if(arr[r][c] == [1][1] || arr[r][c] == [1][2] ||
	// // // 	   arr[r][c] == [2][1] || arr[r][c] == [2][2]){

	// // // 		System.out.println("" + arr[1][1]);
	// // // 		System.out.println("" + arr[1][2]);
	// // // 		System.out.println("" + arr[2][1]);
	// // // 		System.out.println("" + arr[2][2]);
	// // // 	}
	// // // 	if(arr[1][1] == arr[3][3] || arr[1][2] == arr[3][3]
	// // // 		|| arr[2][1] == arr[3][3] || arr[2][2] == arr[3][3]){
	// // // 		swapButton();

	// // // 	}
	// // // }














	






	// // public String swap(){
	// // 	if(arr[0][1] == arr[3][3] || arr[1][0] == arr[3][3]){	
	// // 		swapButton();	
	// // 	}
	// // }


	

	// // public String swapButtonMid(){
	// // 	if(arr[][])





	// // // 	if(arr[1][1] == arr[3][3]){

	// // // 		arr[1][1] = arr[0][0] + arr[1][1];
	// // // 		arr[0][0] = arr[1][1] - arr[0][0];
	// // // 		arr[1][1] = arr[1][1] - arr[0][0];
	// // // 	}
	// // // 	if else(arr[1][2] == arr[3][3]){

	// // // 		arr[1][2] = arr[0][0] + arr[1][2];
	// // // 		arr[0][0] = arr[1][2] - arr[0][0];
	// // 		arr[1][2] = arr[1][2] - arr[0][0];
	// // 	}

	// // 	|| || 
	// // 		arr[2][1] instanceof " " || arr[2][2] instanceof " " )
	// // }

	







}