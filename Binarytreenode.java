public class Binarytreenode
{
    public Binarytreenode left = null;
    public Binarytreenode right = null;
    public Binarytreenode parent=null;
    public int data;

    public Binarytreenode(int data)
    {
        this.data=data;
    }

    public void printNode()
    {
        System.out.println(data);
    }
    



}
