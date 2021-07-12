import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SweetsV2 {

	public static void main(String[] args) {
		
		SweetsV2 me=new SweetsV2();
		try {
			me.doMyThing();
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	public void  doMyThing() throws NumberFormatException, IOException {
		// read in
		BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
		int numberOfChildren = Integer.parseInt(bi.readLine());
		int numberOfSweetTypes = Integer.parseInt(bi.readLine());
		ArrayList<Integer> availableSweets= new ArrayList<Integer>();
		String[] thirdLine= bi.readLine().split(" ");
		int sumSweets=0;
		for(String s: thirdLine) {
			sumSweets+=Integer.parseInt(s);
			availableSweets.add(Integer.parseInt(s));
		}
		
		int numberOfSweetsPerChild=sumSweets/numberOfChildren;
		ArrayList<Child>children=new ArrayList<Child>();
		for(int i=0; i<numberOfChildren;i++) {
			String[] line= bi.readLine().split(" ");
			Child child=new Child(numberOfSweetTypes);
			for(String s:line) {
				child.preferences.add(Integer.parseInt(s));
			}
			children.add(child);
		}
		String output="";
		if (sumSweets >= numberOfChildren) {
			for (int p = 1; p <= numberOfChildren; p++) {
				boolean possible = true;
				for (Child c : children) {
					for (int i = 0; i < c.assignedSweets.length; i++) {
						c.assignedSweets[i] = 0;
					}
					c.sumAssignedSweets = 0;
					c.updateAvailableSweets(p, availableSweets);
					if (c.sumOfSweetsAvailable < numberOfSweetsPerChild) {
						possible = false;
					}
				}
				if (possible) {
					ArrayList<Integer> temp = (ArrayList<Integer>) availableSweets.clone();
					for (Child c : children) {
						for (int i = 0; i < c.preferences.size(); i++) {
							if (c.sumAssignedSweets < numberOfSweetsPerChild && c.preferences.get(i) <= p
									&& temp.get(i) > 0) {
								int min = Math.min(numberOfSweetsPerChild - c.sumAssignedSweets, temp.get(i));
								c.assignedSweets[i] = c.assignedSweets[i] + min;
								temp.set(i, temp.get(i) - min);
								c.sumAssignedSweets += min;
							}
						}
					}
					for (Child c : children) {
						if (c.sumAssignedSweets < numberOfSweetsPerChild) {
							for (int i = 0; i < c.preferences.size(); i++) {
								if (c.preferences.get(i) <= p) {
									for (Child c2 : children) {
										if (c2.assignedSweets[i] > 0) {
											for (int j = 0; j < c2.preferences.size(); j++) {
												if (c2.preferences.get(j) <= p && temp.get(j) > 0) {
													int min = Math
															.min(Math.min(numberOfSweetsPerChild - c.sumAssignedSweets,
																	temp.get(j)), c2.assignedSweets[i]);

													c.assignedSweets[i] += min;
													c.sumAssignedSweets += min;
													c2.assignedSweets[i] -= min;
													c2.assignedSweets[j] += min;
													temp.set(j, temp.get(j) - min);
												}
											}
										}
									}
								}
							}
							if (c.sumAssignedSweets < numberOfSweetsPerChild) {
								possible = false;
								break;
							}
						}
					}
					if (possible) {
						output += p;
						break;
					}

				}

			}
		}
		if(output.equals("")) {
			output+=numberOfChildren+1;
		}
			System.out.println(output);
		
		
	}
	
	public class Child{
		ArrayList<Integer> preferences;
		int sumOfSweetsAvailable;
		int[] assignedSweets;
		int sumAssignedSweets;
		
		public Child(int numberOfSweetTypes) {
			preferences=new ArrayList<Integer>();
			assignedSweets=new int[numberOfSweetTypes];
			sumAssignedSweets=0;
		}
		
		public void updateAvailableSweets(int maxPrio, ArrayList<Integer> sweetsNumbers) {
			sumOfSweetsAvailable=0;
			for(int pref=0; pref< preferences.size();pref++) {
				if(preferences.get(pref)<=maxPrio) {
					sumOfSweetsAvailable+=sweetsNumbers.get(pref);
				}
			}
		}
	}

}
