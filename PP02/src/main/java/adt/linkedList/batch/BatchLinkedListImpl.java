package adt.linkedList.batch;

import adt.linkedList.DoubleLinkedListImpl;
import adt.linkedList.DoubleLinkedListNode;
import util.GenericException;

/**
 * Manipula elementos da LinkedList em bloco (batch).
 * 
 * @author campelo
 * @author adalberto
 *
 * @param <T>
 */
public class BatchLinkedListImpl<T> extends DoubleLinkedListImpl<T> implements BatchLinkedList<T> {

   /*
    * Nao modifique nem remova este metodo.
    */
   public BatchLinkedListImpl() {
      head = new DoubleLinkedListNode<T>();
      last = (DoubleLinkedListNode<T>) head;
   }

   @Override
   public void inserirEmBatch(int posicao, T[] elementos) throws GenericException {

      if (elementos == null || posicao < 0 || (size() != 0 && posicao > size())) {
         throw new GenericException();
      } else {
         DoubleLinkedListNode<T> node = returnNode(posicao);
         if (!node.isNIL()) {

            if (!node.getNext().isNIL() && posicao > 0) {
               DoubleLinkedListNode<T> nextNode = (DoubleLinkedListNode<T>) node.getNext();
               for (int i = 0; i < elementos.length; i++) {
                  DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(elementos[i],
                        new DoubleLinkedListNode<>(), new DoubleLinkedListNode<>());
                  newNode.setPrevious(node);
                  node.setNext(newNode);
                  node = (DoubleLinkedListNode<T>) node.getNext();
               }
               node.setNext(nextNode);
               nextNode.setPrevious(node);

            } else if (posicao == 0) {
               DoubleLinkedListNode<T> nextNode = node;

               setHead((new DoubleLinkedListNode<T>(elementos[0], new DoubleLinkedListNode<>(),
                     new DoubleLinkedListNode<>())));
               node = (DoubleLinkedListNode<T>) getHead();
               DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(null, new DoubleLinkedListNode<>(),
                     new DoubleLinkedListNode<>());
               for (int i = 1; i < elementos.length; i++) {
                  newNode = new DoubleLinkedListNode<T>(elementos[i], new DoubleLinkedListNode<>(),
                        new DoubleLinkedListNode<>());
                  newNode.setPrevious(node);
                  node.setNext(newNode);
                  node = (DoubleLinkedListNode<T>) node.getNext();
               }
               node.setNext(nextNode);
               nextNode.setPrevious(node);

            } else {
               for (int i = 0; i < elementos.length; i++) {
                  DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(elementos[i],
                        new DoubleLinkedListNode<>(), new DoubleLinkedListNode<>());
                  node.setNext(newNode);
                  newNode.setPrevious(node);
                  node = (DoubleLinkedListNode<T>) node.getNext();

               }
               setLast(node);
            }
         } else {
            this.setHead(new DoubleLinkedListNode<T>(elementos[0], new DoubleLinkedListNode<>(),
                  new DoubleLinkedListNode<>()));
            node = (DoubleLinkedListNode<T>) getHead();
            DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(null, new DoubleLinkedListNode<>(),
                  new DoubleLinkedListNode<>());
            ;
            for (int i = 1; i < elementos.length; i++) {
               newNode = new DoubleLinkedListNode<T>(elementos[i], new DoubleLinkedListNode<>(),
                     new DoubleLinkedListNode<>());
               newNode.setPrevious(node);
               node.setNext(newNode);
               node = (DoubleLinkedListNode<T>) node.getNext();
            }
            setLast(newNode);

         }
      }
   }

   @Override
   public void removerEmBatch(int posicao, int quantidade) throws GenericException {
      if (posicao < 0 || quantidade < 0 || quantidade > size()) {
         throw new GenericException();
      } else {
         if (posicao > 0) {
            DoubleLinkedListNode<T> firstNode = returnNode(posicao - 1);
            DoubleLinkedListNode<T> node = returnNode(posicao);
            int i = 0;
            while (i < quantidade) {
               DoubleLinkedListNode<T> nextNode = (DoubleLinkedListNode<T>) node.getNext();
               nextNode.setPrevious(firstNode);
               firstNode.setNext(nextNode);
               
               node = (DoubleLinkedListNode<T>) node.getNext();
               i++;
            }
            this.setLast(findLast());
            node.setPrevious(firstNode);
         } else {
            DoubleLinkedListNode<T> node = returnNode(posicao);
            int i = 0;
            while (i < quantidade) {
               node = (DoubleLinkedListNode<T>) node.getNext();
               node.setPrevious(new DoubleLinkedListNode<T>());
               i++;
            }
            this.setHead(node);
            this.setLast(findLast());

         }

      }
   }
   
   protected DoubleLinkedListNode<T> findLast(){
	   DoubleLinkedListNode<T> node = (DoubleLinkedListNode<T>) this.getHead();
	   while(!node.getNext().isNIL()){
		   node = (DoubleLinkedListNode<T>) node.getNext();
	   }
	   return node;
   }

   protected DoubleLinkedListNode<T> returnNode(int posicao) {
      int index = 0;
      DoubleLinkedListNode<T> node = (DoubleLinkedListNode<T>) this.getHead();
      if (!node.isNIL()) {
         while (!node.getNext().isNIL() && index < posicao) {
            node = (DoubleLinkedListNode<T>) node.getNext();
            index++;

         }
         if (index == posicao) {
            return node;
         }
      }

      return new DoubleLinkedListNode<>();
   }
   
   @Override
   public int size() {
      int size = 0;
      if(!this.getHead().isNIL()){
    	  
    	  DoubleLinkedListNode<T> node = (DoubleLinkedListNode<T>) this.getHead();
    	  
    	  while (!node.getNext().isNIL()) {
    		  size++;
    		  node = (DoubleLinkedListNode<T>) node.getNext();
    	  }
      }
      return size;
   }

   /*
    * NAO MODIFIQUE NEM REMOVA ESTE METODO!!!
    * 
    * Use este metodo para fazer seus testes
    * 
    * Este metodo monta uma String contendo os elementos do primeiro ao ultimo,
    * comecando a navegacao pelo Head
    */
   public String toStringFromHead() {

      String result = "";
      DoubleLinkedListNode<T> aNode = (DoubleLinkedListNode<T>) getHead();

      while (!aNode.isNIL()) {

         if (!result.isEmpty()) {
            result += " ";
         }

         result += aNode.getData();
         aNode = (DoubleLinkedListNode<T>) aNode.getNext();

      }

      return result;
   }

   /*
    * NAO MODIFIQUE NEM REMOVA ESTE METODO!!!
    * 
    * Use este metodo para fazer seus testes
    * 
    * Este metodo monta uma String contendo os elementos do primeiro ao ultimo,
    * porem comecando a navegacao pelo Last
    * 
    * Este metodo produz o MESMO RESULTADO de toStringFromHead()
    * 
    */
   public String toStringFromLast() {

      String result = "";
      DoubleLinkedListNode<T> aNode = getLast();

      while (!aNode.isNIL()) {

         if (!result.isEmpty()) {
            result = " " + result;
         }

         result = aNode.getData() + result;
         aNode = (DoubleLinkedListNode<T>) aNode.getPrevious();

      }

      return result;
   }

   @Override
   public String toString() {
      return toStringFromHead();
   }

}
