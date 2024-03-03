public class Item {
    private String name;
    private String recycling;
    private String alternatives;
    private String reuse;
  
    public Item(String name, String recycling, String alternatives, String reuse) {
      this.name = name;
      this.recycling = recycling;
      this.alternatives = alternatives;
      this.reuse = reuse;
    }
  
    public Item(String input) {
      String[] inputs = input.split("#");
      this.name = inputs[0].trim();
      this.recycling = inputs[1].trim();
      this.alternatives = inputs[2].trim();
      this.reuse = inputs[3].trim();
    }
  
    public String getName() {
      return this.name;
    }
  
    public void setName(String name) {
      this.name = name;
    }
  
    public String getRecycling() {
      return this.recycling;
    }
  
    public void setRecycling(String recycling) {
      this.recycling = recycling;
    }
  
    public String getAlternatives() {
      return this.alternatives;
    }
  
    public void setAlternatives(String alternatives) {
      this.alternatives = alternatives;
    }
  
    public String getReuse() {
      return this.reuse;
    }
  
    public void setReuse(String reuse) {
      this.reuse = reuse;
    }
  
    @Override
    public String toString() {
      return "\n\n\n" + this.name + "\n\nRecycling:\n" + this.recycling + "\n\nAlternatives:\n" + this.alternatives
          + "\n\nReuse:\n" + this.reuse;
    }

    public static String formatStuff(String input) {
        String out = "";
        String[] temp = input.split(", ");
        for (String s : temp) {
            out = out + " - " + s + "\n";
        }
        return out;
    }
  }