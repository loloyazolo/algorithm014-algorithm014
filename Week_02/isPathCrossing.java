class Solution {
    public boolean isPathCrossing(String path) {
        Set<Integer> si = new HashSet<>();
		int x = 0, y = 0;
		si.add(getHash(x, y));
		for(char c : path.toCharArray()) {
			if(c == 'N') {
				y++;
			}else if(c == 'S') {
				y--;
			}else if(c == 'W') {
				x--;
			}else {
				x++;
			}
			int temp = getHash(x, y);
			if(si.contains(temp)) {
				return true;
			}
			si.add(temp);
		}
		return false;
    }
    int getHash(int x, int y) {
		return x * 20001 + y;
	}
}
