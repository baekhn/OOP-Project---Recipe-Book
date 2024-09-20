import java.util.ArrayList;

public class Steps 
{
    private ArrayList<ArrayList<String>> stepsAllRecipe;

    public Steps() 
    {
        stepsAllRecipe = new ArrayList<>();
    }


    public void addNewStepsList(ArrayList<String> newSteps) 
    {
        stepsAllRecipe.add(newSteps);
//        System.out.println("sekarang ada" + stepsAllRecipe.size());
        
    }

    public void addSteptotheList(String step, int stepIndex) 
    {
        if (stepIndex < 0 || stepIndex >= stepsAllRecipe.size()) 
        {
            System.out.println("ID salah!");
            return;
        }
        stepsAllRecipe.get(stepIndex).add(step);
    }

    public ArrayList<String> getRecipeSteps(int recipeIndex) 
    {
    	System.out.println( stepsAllRecipe.size());
        if (recipeIndex < 0 || recipeIndex >= stepsAllRecipe.size()) 
        {
//            System.out.println("Resep tidak ditemukan.");
            return new ArrayList<>();
        }
        return new ArrayList<>(stepsAllRecipe.get(recipeIndex));
    }

    public void setStepInnerList(int recipeIndex, int stepIndex, String newStep) 
    {
        if (recipeIndex < 0 || recipeIndex >= stepsAllRecipe.size() ||
            stepIndex < 0 || stepIndex >= stepsAllRecipe.get(recipeIndex).size()) 
        {
            System.out.println("Index tidak valid.");
            return;
        }
        stepsAllRecipe.get(recipeIndex).set(stepIndex, newStep);
    }

    public void displayRecipeSteps(int recipeIndex) 
    {
        if (recipeIndex < 0 || recipeIndex >= stepsAllRecipe.size()) 
        {
            System.out.println("Resep tidak ditemukan.");
            return;
        }
        System.out.println("Steps " + ":");
        int num = 1;
        for (String step : stepsAllRecipe.get(recipeIndex)) 
        {
            System.out.println(num + "."+ step);
            num++;
        }
    }
    public void removeInnerList(int recipeIndex, int stepIndex)
    {
    	if (recipeIndex < 0 || recipeIndex >= stepsAllRecipe.size() ||
                stepIndex < 0 || stepIndex >= stepsAllRecipe.get(recipeIndex).size()) 
    	{
                System.out.println("Index tidak valid.");
                return;
    	}
    	stepsAllRecipe.get(recipeIndex).remove(stepIndex);
    }
    public void removIngreList(int recipeIndex)
    {
    	if (recipeIndex < 0 || recipeIndex >= stepsAllRecipe.size()) 
    	{
                System.out.println("Index tidak valid.");
                return;
    	}
    	stepsAllRecipe.remove(recipeIndex);
    }
}
