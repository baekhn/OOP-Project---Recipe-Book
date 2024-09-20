import java.util.ArrayList;

public class Ingredients 
{
    private ArrayList<ArrayList<String>> ingreAllRecipe;

    public Ingredients() 
    {
        ingreAllRecipe = new ArrayList<>();
    }

    // Menambahkan nambah ingre list baru dari recipe yg baru di insert
    public void addNewIngreList(ArrayList<String> newRecipe) 
    {
        ingreAllRecipe.add(newRecipe);
//        System.out.println("sekarang ada" + ingreAllRecipe.size());
        
    }

    // Menambahkan ingredient ke resep tertentu
    public void addIngretotheList(String ingredient, int recipeIndex) 
    {
        // Pastikan index valid
        if (recipeIndex < 0 || recipeIndex >= ingreAllRecipe.size()) 
        {
            System.out.println("ID salah!");
            return;
        }
        ingreAllRecipe.get(recipeIndex).add(ingredient);
    }

    // dapet 
    public ArrayList<String> getRecipeIngredients(int recipeIndex) 
    {
    	System.out.println( ingreAllRecipe.size());
        if (recipeIndex < 0 || recipeIndex >= ingreAllRecipe.size()) 
        {
            System.out.println("Resep tidak ditemukan.");
            return new ArrayList<>();
        }
        return new ArrayList<>(ingreAllRecipe.get(recipeIndex));
    }

    // ganti satu list ingredients dari recipe tertentu
    public void setIngreInnerList(int recipeIndex, int ingredientIndex, String newIngredient) 
    {
        if (recipeIndex < 0 || recipeIndex >= ingreAllRecipe.size() ||
            ingredientIndex < 0 || ingredientIndex >= ingreAllRecipe.get(recipeIndex).size()) 
        {
            System.out.println("Index tidak valid.");
            return;
        }
        ingreAllRecipe.get(recipeIndex).set(ingredientIndex, newIngredient);
    }

    // Menampilkan semua ingredients dari resep tertentu
    public void displayRecipeIngredients(int recipeIndex) 
    {
        if (recipeIndex < 0 || recipeIndex >= ingreAllRecipe.size()) 
        {
            System.out.println("Resep tidak ditemukan.");
            return;
        }
        System.out.println("Ingredients " + ":");
        int num = 1;
        for (String ingredient : ingreAllRecipe.get(recipeIndex)) 
        {
            System.out.println(num + "."+ ingredient);
            num++;
        }
    }
    //lupa nambahin buat delete innerlistnya
    public void removeInnerList(int recipeIndex, int ingredientIndex)
    {
    	if (recipeIndex < 0 || recipeIndex >= ingreAllRecipe.size() ||
                ingredientIndex < 0 || ingredientIndex >= ingreAllRecipe.get(recipeIndex).size()) 
    	{
                System.out.println("Index tidak valid.");
                return;
    	}
    	ingreAllRecipe.get(recipeIndex).remove(ingredientIndex);
    }
    public void removIngreList(int recipeIndex)
    {
    	if (recipeIndex < 0 || recipeIndex >= ingreAllRecipe.size()) 
    	{
                System.out.println("Index tidak valid.");
                return;
    	}
    	ingreAllRecipe.remove(recipeIndex);
    }
}
