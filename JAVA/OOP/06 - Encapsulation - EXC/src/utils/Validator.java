package utils;

public class Validator {
  public static void validateToppingWeight(double weight, String topping){
      if (weight < 1 || weight > 50) {
          throw new IllegalArgumentException(String.format("%s weight should be in the range [1..50].",
                  topping));
          //{Topping type name}
      }
  }

    public static void validateDoughWeight(double weight){
        if (weight < 1 || weight > 200) {
            throw new IllegalArgumentException("Dough weight should be in the range [1..200].");
        }
    }
}
