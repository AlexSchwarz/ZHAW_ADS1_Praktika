package Praktikum13;

public class TestCObjectWithGenerationServer implements CommandExecutor  {

   private static CObject new_CObject(Object s) {
      CObject obj = (CObject) StorageWithGeneration._new("Praktikum13.CObject",s);
      return obj;
   }

   static CObject a;
   static CObject e;

   public String execute (String input) {
      run();
      return StorageWithGeneration.getLog();
   }


   private void run() {
      a = new_CObject("A");
      CObject b = new_CObject("B");
      CObject c = new_CObject("C");
      CObject d = new_CObject("D");
      e = new_CObject("E");
      CObject f = new_CObject("F");
      CObject g = new_CObject("G");
      CObject h = new_CObject("H");
      StorageWithGeneration.addRoot(a);
      StorageWithGeneration.addRoot(e);
      a.next = b; b.next = c; b.down = a; c.down = d;
      e.next = f; f.next = g; g.next = e;
      StorageWithGeneration.dump("\nRoots:", StorageWithGeneration.getRoot());
      StorageWithGeneration.dump("Heap 1 young heap:", StorageWithGeneration.getYoungGeneration());
      StorageWithGeneration.dump("Heap 1 old heap:", StorageWithGeneration.getOldGeneration());
      StorageWithGeneration.gc();
      StorageWithGeneration.dump("Heap 2 young heap:", StorageWithGeneration.getYoungGeneration());
      StorageWithGeneration.dump("Heap 2 old heap:", StorageWithGeneration.getOldGeneration());
      b.next = f;
      StorageWithGeneration.gc();
      StorageWithGeneration.dump("Heap 3 young heap:", StorageWithGeneration.getYoungGeneration());
      StorageWithGeneration.dump("Heap 3 old heap:", StorageWithGeneration.getOldGeneration());
      f.next = null;
      StorageWithGeneration.gc();
      StorageWithGeneration.dump("Heap 4 young heap:", StorageWithGeneration.getYoungGeneration());
      StorageWithGeneration.dump("Heap 4 old heap:", StorageWithGeneration.getOldGeneration());
      StorageWithGeneration.gc();
      StorageWithGeneration.dump("Heap 5 young heap:", StorageWithGeneration.getYoungGeneration());
      StorageWithGeneration.dump("Heap 5 old heap:", StorageWithGeneration.getOldGeneration());
   }

}
