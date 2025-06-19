// nó da ABB
class Node {
    int key;
    Node left, right;

    public Node(int key) {
        this.key = key;
        this.left = null;
        this.right = null;
    }
}

// representa a ABB
public class At14 {
    private Node root;

    public At14() {
        this.root = null;
    }

    // inserir nó na ABB
    public void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }
        if (key < node.key) {
            node.left = insertRec(node.left, key);
        } else if (key > node.key) {
            node.right = insertRec(node.right, key);
        }
        return node;
    }

    // buscar nó na ABB
    public boolean search(int key) {
        return searchRec(root, key) != null;
    }

    private Node searchRec(Node node, int key) {
        if (node == null || node.key == key) {
            return node;
        }
        if (key < node.key) {
            return searchRec(node.left, key);
        }
        return searchRec(node.right, key);
    }

    // impressão
    public void printInOrder() {
        printInOrderRec(root);
        System.out.println();
    }

    private void printInOrderRec(Node node) {
        if (node != null) {
            printInOrderRec(node.left);
            System.out.print(node.key + " ");
            printInOrderRec(node.right);
        }
    }

    public void printPreOrder() {
        printPreOrderRec(root);
        System.out.println();
    }

    private void printPreOrderRec(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            printPreOrderRec(node.left);
            printPreOrderRec(node.right);
        }
    }

    public void printPostOrder() {
        printPostOrderRec(root);
        System.out.println();
    }

    private void printPostOrderRec(Node node) {
        if (node != null) {
            printPostOrderRec(node.left);
            printPostOrderRec(node.right);
            System.out.print(node.key + " ");
        }
    }

    // remover nó na ABB
    public void remove(int key) {
        root = removeRec(root, key);
    }

    private Node removeRec(Node node, int key) {
        if (node == null) {
            return null;
        }

        // procura nó pra ser removido
        if (key < node.key) {
            node.left = removeRec(node.left, key);
        } else if (key > node.key) {
            node.right = removeRec(node.right, key);
        } else {
            // caso 1 - nó folha
            if (node.left == null && node.right == null) {
                return null;
            }

            // caso 2: nó com um filho
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            // caso 3: nó com dois filhos
            Node successor = findMin(node.right);
            node.key = successor.key;
            node.right = removeRec(node.right, successor.key);
        }

        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // método principal para testar a ABB
    public static void main(String[] args) {
        At14 abb = new At14();

        // Inserindo nós
        abb.insert(50);
        abb.insert(30);
        abb.insert(70);
        abb.insert(20);
        abb.insert(40);
        abb.insert(60);
        abb.insert(80);

       
        System.out.println("Impressão em ordem:");
        abb.printInOrder();

        System.out.println("Impressão pré-ordem:");
        abb.printPreOrder();

        System.out.println("Impressão pós-ordem:");
        abb.printPostOrder();

        
        System.out.println("\nBuscando o nó com chave 40:");
        System.out.println(abb.search(40) ? "Encontrado!" : "Não encontrado!");

        
        System.out.println("\nRemovendo o nó com chave 70:");
        abb.remove(70);
        abb.printInOrder();
    }
}