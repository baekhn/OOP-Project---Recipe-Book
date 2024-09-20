import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;


public class Main 
{
	static ArrayList <recipe> rec = new ArrayList<recipe>();
	Ingredients ingredients = new Ingredients();
	Steps step = new Steps();
	Scanner sc = new Scanner(System.in);

	public Main() 
	{
		int opt = 0;
		do
		{
			// TODO Auto-generated constructor stub
			try 
			{
				System.out.println("\n");
			    System.out.println("*************************************");
			    System.out.println("*    WELCOME TO THE RECIPE SYSTEM   *");
			    System.out.println("*      Explore, Cook, and Enjoy!    *");
	            System.out.println("*************************************");
				System.out.println("===========");
				System.out.println("1. Create New");
				System.out.println("2. See All");
				System.out.println("3. Update Recipe");
				System.out.println("4. Delete");
				System.out.println("5. Exit");
				System.out.print(">");
				opt = sc.nextInt();
				sc.nextLine();
				
				switch(opt)
				{
				case 1:
					addRecipe();
					break;
				case 2:
					display();
					break;
				case 3:
					updateRecipe();
					break;
				case 4:
					deleteRecipe();
					break;
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Error: Invalid input. Please enter a valid option.");
				sc.nextLine();
			}
		}while (opt != 5);
		
	}
	int index = 0;
	
	public void displayCodeWrong()
	{
		System.out.println("================");
		System.out.println(" Code is wrong! ");
		System.out.println("================");
		System.out.println("Press ENTER to continue...");
		sc.nextLine();
	}
	public void displayNoData()
	{
		System.out.println("===================");
		System.out.println(" There is no data! ");
		System.out.println("===================");
		System.out.println("Press ENTER to continue...");
		sc.nextLine();
	}
	public void displayUpdate()
	{
		System.out.println("===================");
		System.out.println(" Update success! ");
		System.out.println("===================");
		System.out.println("Press ENTER to continue...");
		sc.nextLine();
	}
	private void addRecipe() 
	{
		// TODO Auto-generated method stub
		//1. input nama
		String inputName;
		do
		{
			System.out.print("Recipe Name [>=3]: ");
			inputName = sc.nextLine();
		}while(!(inputName.length() >= 3));
		
		//2. input category
		String inputCat;
		do
		{
			System.out.print("Choose Category [Food | Beverage]: ");
			inputCat = sc.nextLine();
			
		}while(!(inputCat.equals("Food")|| inputCat.equals("Beverage")));
		
		//3. input type
		// ini makanan
		String inputTypeF = null;
		String inputVegan = null;
		String inputTypeD = null;
		if(inputCat.equals("Food"))
		{
			//desserts main course
			do
			{
				System.out.print("[Desserts | Main Course](Case Sensitive): ");
				inputTypeF = sc.nextLine();
			}while(!(inputTypeF.equals("Desserts")|| inputTypeF.equals("Main Course")));
			
			//vegan non vegan
			do
			{
				System.out.print("Vegan | Non Vegan (Sensitive Case): ");
				inputVegan = sc.nextLine();
			}while(!(inputVegan.equals("Vegan")|| inputVegan.equals("Non Vegan")));
			
			//ID
			
			
		}
		else if(inputCat.equals("Beverage"))
		{
			//type
			do
			{
				System.out.print("Choose Category [Soda | Tea | Coffee | Fruits]: ");
				inputTypeD = sc.nextLine();
				
			}while(!(inputTypeD.equals("Soda")|| inputTypeD.equals("Tea")|| inputTypeD.equals("Coffee")||inputTypeD.equals("Fruits")));
			
		}
		
		//validate prep time and cook time
		Pattern pattern = Pattern.compile("\\d+ (sec|min|hours|hour)");
		// 5.prep time 
		String inputprepTime;
		Matcher matcher1;
		do
		{
			System.out.print("Preparation Time (must end with [sec | min | hours]): ");
			inputprepTime = sc.nextLine();
			matcher1 = pattern.matcher(inputprepTime);
		
		}while(matcher1.matches() == false);
		// 6. cook time
		Matcher matcher2;
		String inputcookTime;
		do
		{
			System.out.print("Cooking Time (must end with [sec | min | hours]): ");
			inputcookTime = sc.nextLine();
			matcher2 = pattern.matcher(inputcookTime);
		
		}while(matcher2.matches() == false);
		
		//input ingre
		ArrayList<String> listIngrePerRecipe = new ArrayList<>();
		while(true)
		{
			System.out.println("Enter ingredients [Input '0' if done]: ");
			String addIngre = sc.nextLine();
			if(addIngre.equals("0"))
			{
				break;
			}
			listIngrePerRecipe.add(addIngre);
			
			
		}
		ingredients.addNewIngreList(listIngrePerRecipe);
		
		//input steps 
		ArrayList<String> listStepsPerRecipe = new ArrayList<>();
		while(true)
		{
			System.out.println("Enter steps [Input '0' if done]: ");
			String addSteps = sc.nextLine();
			if(addSteps.equals("0"))
			{
				break;
			}
			listStepsPerRecipe.add(addSteps);
			
		}
		
		step.addNewStepsList(listStepsPerRecipe);
		//geneate ID
		String inputID =  null;
		int x = (int) (Math.random() * 10);
		int y = (int) (Math.random() * 10);
		int z = (int) (Math.random() * 10);
		if(inputCat.equals("Food"))
		{
			inputID = "F" + x + y +  z;
			//input food
			rec.add(new food(inputID, inputName, inputCat, inputTypeF, inputVegan, inputprepTime, inputcookTime));
		}
		else if(inputCat.equals("Beverage"))
		{
			inputID = "B" + x + y +  z;
			//input beverage
			rec.add(new beverage(inputID, inputName, inputCat, inputTypeD, inputprepTime, inputcookTime));
		}
		System.out.println("========================");
		System.out.println(" Add new recipe success!");
		System.out.println("========================");
		System.out.println("Press ENTER to continue...");
		sc.nextLine();
}
		
	

	private void display() 
	{
		// TODO Auto-generated method stub
			
		if(rec.isEmpty())
		{
			displayNoData();
			
		}
		else 
		{
			System.out.println("\n");
			System.out.println("Food:");
			System.out.println("===========================================================");
			System.out.printf("%6s | %10s |%10s | %10s | %10s | %n" , "ID", "Name", "Category", "Time Prep", "Cook Time");
			System.out.println("===========================================================");
			
			for (recipe recipess: rec)
			{
				if (recipess instanceof food)
				{
					System.out.printf("%6s | %10s | %10s | %10s | %10s\n", recipess.ID, recipess.getName(), recipess.getCategory(), recipess.getPrepTime(), recipess.getCookTime());
					
				}
			}
			System.out.println("\n");
			System.out.println("Beverage:");
			System.out.println("===========================================================");
			System.out.printf("%6s | %10s |%10s | %10s | %10s | %n" , "ID", "Name", "Category", "Time Prep", "Cook Time");
			System.out.println("===========================================================");
			
			for (recipe recipess: rec)
			{
				if (recipess instanceof beverage)
				{
					System.out.printf("%6s | %10s | %10s | %10s | %10s\n", recipess.ID, recipess.getName(), recipess.getCategory(), recipess.getPrepTime(), recipess.getCookTime());
				}
			}
			String inputView;
			do
			{
				System.out.print("Input ID to see details [0 for back to Menu]: ");
				inputView = sc.nextLine();
				
				if(inputView.equals("0"))
				{
					new Main();
				}
			}while(!((inputView.length() == 4)&&(inputView.charAt(0) == 'F' || inputView.charAt(0) == 'B')));
			for(int i=0; i<rec.size(); i++)
			{
				
				if(rec.get(i).getID().equals(inputView))
				{
					System.out.println("\n");
					recipe obj = rec.get(i);
					System.out.println("Here is the detail!");
					System.out.println();
					if(rec.get(i).getCategory().equals("Food"))
					{
						
						System.out.println("Recipe Name:" + obj.getName());
						System.out.println("Category: " + obj.getCategory());
						System.out.println("Type: " + ((food)obj).getType());
						System.out.println("Vegan / Non Vegan: " + ((food)obj).getVegan());
						System.out.println("Preparation Time: " + obj.getPrepTime());
						System.out.println("Cook Time: " + obj.getCookTime());
						ingredients.displayRecipeIngredients(i);
						step.displayRecipeSteps(i);
						System.out.println();
						System.out.println("Press ENTER to continue...");
						sc.nextLine();
						break;
						
					}
					else if(obj.getCategory().equals("Beverage"))
					{
						System.out.println("Recipe Name:" + obj.getName());
						System.out.println("Category: " + obj.getCategory());
						System.out.println("Type: " + ((beverage)obj).getType());
						System.out.println("Preparation Time: " + obj.getPrepTime());
						System.out.println("Cook Time: " + obj.cookTime);
						ingredients.displayRecipeIngredients(i);
						step.displayRecipeSteps(i);
						System.out.println();
						System.out.println("Press ENTER to continue...");
						sc.nextLine();
						break;
					}
					
				}
				else
				{
					displayCodeWrong();
				}
			}

		}
		
	}

	private void updateRecipe() 
	{
		// TODO Auto-generated method stub
		String updateCode = null;
		
		if(rec.isEmpty()) displayNoData();
		else
		{
			if(rec.isEmpty())
			{
				System.out.println("There is no data!");
				
			}
			else 
			{
				System.out.println("\n");
				System.out.println("Food:");
				System.out.println("===========================================================");
				System.out.printf("%6s | %10s |%10s | %10s | %10s | %n" , "ID", "Nama", "Category", "Time Prep", "Cook Time");
				System.out.println("===========================================================");
				
				for (recipe recipess: rec)
				{
					if (recipess instanceof food)
					{
						System.out.printf("%6s | %10s | %10s | %10s | %10s\n", recipess.ID, recipess.getName(), recipess.getCategory(), recipess.getPrepTime(), recipess.getCookTime());
						
					}
				}
				System.out.println("\n");
				System.out.println("Beverage:");
				System.out.println("===========================================================");
				System.out.printf("%6s | %10s |%10s | %10s | %10s | %n" , "ID", "Nama", "Category", "Time Prep", "Cook Time");
				System.out.println("===========================================================");
				
				for (recipe recipess: rec)
				{
					if (recipess instanceof beverage)
					{
						System.out.printf("%6s | %10s | %10s | %10s | %10s\n", recipess.ID, recipess.getName(), recipess.getCategory(), recipess.getPrepTime(), recipess.getCookTime());
					}
				}
			
			do
			{
					System.out.print("Input ID to update details [0 for back to Menu]: ");
					updateCode = sc.nextLine();
					
					if(updateCode.equals("0"))
					{
						new Main();
					}
					Pattern pattern = Pattern.compile("\\d+ (sec|min|hours)");
					for(int i = 0; i<rec.size(); i++)
					{
						if(rec.get(i).getID().equals(updateCode))
						{
							recipe obj = rec.get(i);
							if(rec.get(i).getCategory().equals("Food"))
							{
								
								int opt;
								do
								{
									System.out.println();
									System.out.println("!! Must delete recipe first to change the category !!");
									System.out.println("\n");
									System.out.println("Recipe Name:" + obj.getName());
									System.out.println("Category: " + obj.getCategory());
									System.out.println("Type: " + ((food)obj).getType());
									System.out.println("Vegan / Non Vegan: " + ((food)obj).getVegan());
									System.out.println("Preparation Time: " + obj.getPrepTime());
									System.out.println("Cook Time: " + obj.getCookTime());
									ingredients.displayRecipeIngredients(i);
									step.displayRecipeSteps(i);
									System.out.println();
									System.out.println("What do you want to update?");
									System.out.println();
									System.out.println("1. Recipe Name");
									System.out.println("2. Type");
									System.out.println("3. Vegan/Non Vegan");
									System.out.println("4. Preparation Time");
									System.out.println("5. Cooking Time");
									System.out.println("6. Ingredients");
									System.out.println("7. Steps");
									System.out.println("0. Back to Main");
									System.out.print(">");
									opt = sc.nextInt();
									sc.nextLine();
									
//									
									if(opt == 1) //ganti nama
									{
//										System.out.println("Recipe Name:" + obj.getName());
										String updateName;
										System.out.print("Enter update name [>=3]: ");
										updateName = sc.nextLine();
										rec.get(i).setName(updateName);
										
										displayUpdate();
									}

									else if(opt == 2)//ganti type
									{
										String updateTypeF;
										do
										{
											System.out.print("Input update type [Desserts | Main Course]: ");
											updateTypeF = sc.nextLine();
										}while(!(updateTypeF.equals("Desserts")|| updateTypeF.equals("Main Course")));
										
										((food)obj).setType(updateTypeF);
										
										displayUpdate();
									}
									else if(opt == 3)//ganti vegan non vegan
									{
										String updateVegan;
										do
										{
											System.out.print("Input update vegan[Vegan | Non Vegan]: ");
											updateVegan = sc.nextLine();
											
										}while(!(updateVegan.equals("Vegan")|| updateVegan.equals("Non Vegan")));
										
										((food)obj).setVegan(updateVegan);
										
										displayUpdate();
									}
									
									else if(opt == 4)//ganti prep time
									{
										
										// prep time 
										String updatePrepTime;
										Matcher matcher1;
										do
										{
											System.out.print("Input update preparation time (must end with [sec | min | hours]): ");
											updatePrepTime = sc.nextLine();
											matcher1 = pattern.matcher(updatePrepTime);
											
										}while(matcher1.matches() == false);
										
										obj.setPrepTime(updatePrepTime);
										
										displayUpdate();
										
									}
									else if(opt == 5)//ganti cook time
									{
										String updateCookTime;
										Matcher matcher2;
										do
										{
											System.out.print("Input update cook time (must end with [sec | min | hours]): ");
											updateCookTime = sc.nextLine();
											matcher2 = pattern.matcher(updateCookTime);
										}while(matcher2.matches() == false);
										
										obj.setCookTime(updateCookTime);
										
										displayUpdate();
									}
									else if(opt == 6) //update ingredients
									{
										int optIng;
										do
										{
											ingredients.displayRecipeIngredients(i);
											System.out.println();
//											System.out.println(ingredients.getRecipeIngredients(i).size()); 
											System.out.println("1. Change ingredient ");
											System.out.println("2. Input new ingredient");
											System.out.println("3. Delete ingredient");
											System.out.println("0. Back to update menu");
											System.out.print(">");
											optIng = sc.nextInt();
											sc.nextLine();
											
											if(optIng == 1)//ganti ingre
											{
												int chooseIngre;
												do
												{
													System.out.print("Choose Ingredients that want to be change:");
													chooseIngre = sc.nextInt();
													sc.nextLine();
												}
												while(chooseIngre < 1 && chooseIngre > ingredients.getRecipeIngredients(i).size());
												
												String updateIngre;
												System.out.print("Enter updated ingredient: ");
												updateIngre = sc.nextLine();
												
												ingredients.setIngreInnerList(i, chooseIngre-1, updateIngre);
												displayUpdate();
											}
											else if(optIng == 2)//nambah ingre
											{
												String newIngre;
												System.out.print("Enter new ingredients: ");
												newIngre = sc.nextLine();
												
												ingredients.addIngretotheList(newIngre, i);
												displayUpdate();
											}
											else if(optIng == 3)
											{
												int deleteIngreList;
												do
												{
													System.out.print("Enter number of ingredient that want to be delete:");
													deleteIngreList = sc.nextInt();
												}while(deleteIngreList < 0 && deleteIngreList > ingredients.getRecipeIngredients(i).size());
												ingredients.removeInnerList(i, deleteIngreList-1);
												displayUpdate();
												
											}
											
										}while(optIng != 0);
									}
									else if(opt == 7)//update steps
									{
										int optStep;
										do
										{
											step.displayRecipeSteps(i);
											System.out.println();
//								System.out.println(ingredients.getRecipeIngredients(i).size()); 
											System.out.println("1. Change step ");
											System.out.println("2. Input new step");
											System.out.println("3. Delete step");
											System.out.println("0. Back to update menu");
											System.out.print(">");
											optStep = sc.nextInt();
											sc.nextLine();
											
											if(optStep == 1)//ganti ingre
											{
												int chooseStep;
												do
												{
													System.out.print("Choose step that want to be change:");
													chooseStep = sc.nextInt();
													sc.nextLine();
												}
												while(chooseStep < 1 && chooseStep > step.getRecipeSteps(i).size());
												
												
												String updateStep;
												System.out.print("Enter updated ingredient: ");
												updateStep = sc.nextLine();
												
												step.setStepInnerList(i, chooseStep-1, updateStep);
												displayUpdate();
											}
											else if(optStep == 2)//nambah ingre
											{
												String newStep;
												System.out.print("Enter new step: ");
												newStep = sc.nextLine();
												
												step.addSteptotheList(newStep, i);
												displayUpdate();
											}
											else if(optStep == 3)
											{
												int deleteStepList;
												do
												{
													System.out.print("Enter number of ingredient that want to be delete:");
													deleteStepList = sc.nextInt();
												}while(deleteStepList < 0 && deleteStepList > step.getRecipeSteps(i).size());
												step.removeInnerList(i, deleteStepList-1);
												displayUpdate();
												
											}
											
											
										}while(optStep != 0);
									}
									
								}while(opt != 0);
							}
							else if(rec.get(i).getCategory().equals("Beverage"))
							{
								int opt;
//								recipe objB = rec.get(i);
								do
								{
									System.out.println();
									System.out.println("!! Must delete recipe first to change the category !!");
									System.out.println("\n");
									System.out.println("What do you want to change");
									System.out.println();
									System.out.println("1. Recipe Name");
//									System.out.println("2. Category");
									System.out.println("2. Type");
									System.out.println("3. Preparation Time");
									System.out.println("4. Cook Time");
									System.out.println("5. Ingredients");
									System.out.println("6. Steps");
									System.out.println("0. Back to Main");
									System.out.print(">");
									opt = sc.nextInt();
									sc.nextLine();
									
									if(opt == 1) //ganti nama
									{
										String updateName;
										System.out.print("Input update name [>=3]: ");
										updateName = sc.nextLine();
										obj.setName(updateName);
										
										displayUpdate();
									}

									else if(opt == 2)//ganti type
									{
										String updateTypeF;
										do
										{
											System.out.print("Input update type [Desserts | Main Course]: ");
											updateTypeF = sc.nextLine();
										}while(!(updateTypeF.equals("Desserts")|| updateTypeF.equals("Main Course")));
										((beverage)obj).setType(updateTypeF);
										
										displayUpdate();
									}
									else if(opt == 3)//ganti vegan non vegan
									{
										String updateVegan;
										do
										{
											System.out.print("Input update vegan [Vegan | Non Vegan]: ");
											updateVegan = sc.nextLine();
											
										}while(!(updateVegan.equals("Vegan")|| updateVegan.equals("Non Vegan")));
										
										((food)obj).setVegan(updateVegan);
										
										displayUpdate();
									}
									
									else if(opt == 4)//ganti prep time
									{
										
										// prep time 
										String updatePrepTime;
										Matcher matcher1;
										do
										{
											System.out.print("Input update preparation time (must end with [sec | min | hours]): ");
											updatePrepTime = sc.nextLine();
											matcher1 = pattern.matcher(updatePrepTime);
											
										}while(matcher1.matches() == false);
										
										obj.setPrepTime(updatePrepTime);
										
										displayUpdate();
										
									}
									else if(opt == 5)//ganti cook time
									{
										String updateCookTime;
										Matcher matcher2;
										do
										{
											System.out.print("Input update cook time (must end with [sec | min | hours]):");
											updateCookTime = sc.nextLine();
											matcher2 = pattern.matcher(updateCookTime);
										}while(matcher2.matches() == false);
										
										obj.setCookTime(updateCookTime);
										
										displayUpdate();
									}
									else if(opt == 6) //update ingredients
									{
										int optIng;
										do
										{
											ingredients.displayRecipeIngredients(i);
											System.out.println();
//											System.out.println(ingredients.getRecipeIngredients(i).size()); 
											System.out.println("1. Change ingredients ");
											System.out.println("2. Input new ingredients");
											System.out.println("3. Delete ingredient");
											System.out.println("0. Back to update menu");
											System.out.print(">");
											optIng = sc.nextInt();
											sc.nextLine();
											
											if(optIng == 1)//ganti ingre
											{
												int chooseIngre;
												do
												{
													System.out.print("Choose Ingredients that want to be change:");
													chooseIngre = sc.nextInt();
													sc.nextLine();
												}
												while(chooseIngre < 1 && chooseIngre > ingredients.getRecipeIngredients(i).size());
												
												String updateIngre;
												System.out.print("Enter updated ingredient: ");
												updateIngre = sc.nextLine();
												
												ingredients.setIngreInnerList(i, chooseIngre-1, updateIngre);
												displayUpdate();
											}
											else if(optIng == 2)//nambah ingre
											{
												String newIngre;
												System.out.print("Enter new ingredients: ");
												newIngre = sc.nextLine();
												
												ingredients.addIngretotheList(newIngre, i);
												displayUpdate();
											}
											else if(optIng == 3)
											{
												int deleteIngreList;
												do
												{
													System.out.print("Enter number of ingredient that want to be delete:");
													deleteIngreList = sc.nextInt();
												}while(deleteIngreList < 0 && deleteIngreList > ingredients.getRecipeIngredients(i).size());
												ingredients.removeInnerList(i, deleteIngreList-1);
												displayUpdate();
												
											}
											
										}while(optIng != 0);
									}
									else if(opt == 7)//update steps
									{
										int optStep;
										do
										{
											step.displayRecipeSteps(i);
//								System.out.println(ingredients.getRecipeIngredients(i).size()); 
											System.out.println("1. Change step ");
											System.out.println("2. Input new step");
											System.out.println("3. Delete step");
											System.out.println("0. Back to update menu");
											System.out.print(">");
											optStep = sc.nextInt();
											sc.nextLine();
											
											if(optStep == 1)//ganti ingre
											{
												int chooseStep;
												do
												{
													System.out.print("Choose step that want to be change:");
													chooseStep = sc.nextInt();
													sc.nextLine();
												}
												while(chooseStep < 1 && chooseStep > step.getRecipeSteps(i).size());
												
												
												String updateStep;
												System.out.print("Enter updated step: ");
												updateStep = sc.nextLine();
												
												step.setStepInnerList(i, chooseStep, updateStep);
												displayUpdate();
											}
											else if(optStep == 2)//nambah ingre
											{
												String newStep;
												System.out.print("Enter new step: ");
												newStep = sc.nextLine();
												
												step.addSteptotheList(newStep, i);
												displayUpdate();
											}
											else if(optStep == 3)
											{
												int deleteStepList;
												do
												{
													System.out.println("Enter number of step that want to be delete:");
													deleteStepList = sc.nextInt();
												}while(deleteStepList < 0 && deleteStepList > step.getRecipeSteps(i).size());
												step.removeInnerList(i, deleteStepList-1);
												displayUpdate();
												
											}
											
										}while(optStep != 0);
									}
								
									
								}while(opt != 0);
							
							}

						}
						else
						{
							displayCodeWrong();
						}
				
		
					}
				}while(!((updateCode.length() == 4)&&(updateCode.charAt(0) == 'F' || updateCode.charAt(0) == 'B')));
			

			}
		}
	}

	private void deleteRecipe() 
	{
		// TODO Auto-generated method stub
//		int flag;
		if(rec.isEmpty()) displayNoData(); 
		else
		{
			System.out.println("\n");
			System.out.println("Food:");
			System.out.println("===========================================================");
			System.out.printf("%6s | %10s |%10s | %10s | %10s | %n" , "ID", "Nama", "Category", "Time Prep", "Cook Time");
			System.out.println("===========================================================");
			
			for (recipe recipess: rec)
			{
				if (recipess instanceof food)
				{
					System.out.printf("%6s | %10s | %10s | %10s | %10s\n", recipess.ID, recipess.getName(), recipess.getCategory(), recipess.getPrepTime(), recipess.getCookTime());
					
				}
			}
			System.out.println("\n");
			System.out.println("Beverage:");
			System.out.println("===========================================================");
			System.out.printf("%6s | %10s |%10s | %10s | %10s | %n" , "ID", "Nama", "Category", "Time Prep", "Cook Time");
			System.out.println("===========================================================");
			
			for (recipe recipess: rec)
			{
				if (recipess instanceof beverage)
				{
					System.out.printf("%6s | %10s | %10s | %10s | %10s\n", recipess.ID, recipess.getName(), recipess.getCategory(), recipess.getPrepTime(), recipess.getCookTime());
				}
			}
			
			String deleteID;
			do
			{
				System.out.println("Input ID: ");
				deleteID = sc.nextLine();
				
			}while(!((deleteID.length() == 4)&&(deleteID.charAt(0) == 'F' || deleteID.charAt(0) == 'B')));
			
			for(int i=0; i<rec.size(); i++)
			{
				
				if(rec.get(i).getID().equals(deleteID))
				{
					rec.remove(i);
					ingredients.removIngreList(i);
					step.removIngreList(i);
					System.out.println("================");
					System.out.println(" Delete success!");
					System.out.println("================");
					System.out.println("Press ENTER to continue...");
					sc.nextLine();
					break;
				}
				else 
				{
					displayCodeWrong();
				}
			}
		}
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
		System.out.println("===============================");
		System.out.println("          Thank You ! ");
		System.out.println("===============================");

	}

}
