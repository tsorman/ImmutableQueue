package com.tsorman.immutable_queue;

import java.util.NoSuchElementException;

/**
 * An implementation in Java of an immutable Queue.
 * @author Nikos Tsorman 
 *
 */
public final class ImmutableQueue<T> implements Queue<T>{

	/**
	 * This stack holds all the incoming items.
	 */
	private final ImmutableStack<T> inputStack;
	
	/**
	 * This stack holds the outgoing items and is created by reversing the {@link #inputStack}.
	 */
	private final ImmutableStack<T> outputStack;
	
	/**
	 * Creating a new empty {@link com.tsorman.immutable_queue.ImmutableQueue}.
	 */
	public ImmutableQueue () {
		inputStack = new ImmutableStack<T> () ;
		outputStack = new ImmutableStack<T> () ;
	}
	
	/**
	 * Creates a new {@link com.tsorman.immutable_queue.ImmutableQueue} with the given stacks. Used internally to create Queues during operations.
	 * @param inputStack the {@link #inputStack} for the new Queue
	 * @param outputStack the {@link #outputStack} for the new Queue
	 */
	private ImmutableQueue ( ImmutableStack<T> inputStack , ImmutableStack<T> outputStack ) {
		this.inputStack = inputStack ;
		this.outputStack = outputStack;
	}
	
	/**
	 * Returns a new {@link com.tsorman.immutable_queue.Queue} with the item passed included. 
	 * @param t the item to be added in the new Queue.
	 * @return a new Queue that has the item added to it. 
	 */
	@Override
	public Queue<T> enQueue(T t) {
		
		if ( t == null )
			throw new IllegalArgumentException() ;
		
			return new ImmutableQueue<T>(this.inputStack.push(t), this.outputStack);		
	}

	/**
	 * DeQueues one item and returns the rest of the Queue. This is done by returning a new {@link com.tsorman.immutable_queue.ImmutableQueue} that
	 * contains the {@link #inputStack} and the tail of the existing {@link #outputStack}. 
	 * If the {@link #outputStack} is empty, a copy is returned with the reversed {@link #inputStack} as the {@link #outputStack} and a new empty {@link #inputStack}.
	 * @return a copy of the tail of the current  {@link com.tsorman.immutable_queue.ImmutableQueue}
	 * @throws {@link NoSuchElementException} if the Queue is empty.
	 */
	@Override
	public Queue<T> deQueue() {
		if ( this.isEmpty())
			throw new NoSuchElementException() ;
		
		if ( ! this.outputStack.isEmpty()) {
			return new ImmutableQueue<T> ( this.inputStack , this.outputStack.stackTail) ;
		}
		
		return new ImmutableQueue<T> ( new ImmutableStack<T>() , this.inputStack.reverse().stackTail) ;
		 
	}
	/**
	 * Returns the head of this {@link com.tsorman.immutable_queue.ImmutableQueue}.
	 * @return 	a copy of the item that is the head of the Queue. First the {@link #outputtStack} is being checked. If not empty the head of it is returned.
	 * 			If its empty, then we get a copy of the reversed {@link #inputStack} and we get its head. 
	 * @throws {@link NoSuchElementException} in the case that this {@link com.tsorman.immutable_queue.ImmutableQueue} is empty. 
	 */
	@Override
	public T head() {
		// TODO Auto-generated method stub
		
		if ( this.isEmpty()) {
			throw new NoSuchElementException() ;
		}
		
		if ( this.outputStack.isEmpty() )
		{
			return new ImmutableQueue<T> ( new ImmutableStack<T>() , this.inputStack.reverse()).head() ;
		}
		
		return this.outputStack.head;
	}
	
	/**
	 * Check if this {@link com.tsorman.immutable_queue.ImmutableQueue} is empty. 
	 * @return true if both the {@link #inputStack} and the {@link #outputStack} are empty.
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.inputStack.isEmpty() && this.outputStack.isEmpty();
	}
}
	
	/**
	 * An implementation in Java of an immutable Stack. This is used for the implementation of {@link com.tsorman.immutable_queue.ImmutableQueue}
	 * @author Nikos Tsorman 
	 */
	final class ImmutableStack<T> {
		/**
		 * The head of this Stack.
		 */
		final T head ;
		
		/**
		 * The tail of this stack.
		 */
		final ImmutableStack<T> stackTail ;
		
		/**
		 * Creates a new empty {@link com.tsorman.immutable_queue.ImmutableQueue.ImmutableStack}.
		 */
		ImmutableStack () {
			this.head = null ;
			this.stackTail = null ;
		}
		
		/**
		 * Creates a new {@link com.tsorman.immutable_queue.ImmutableQueue.ImmutableStack} with the given {@link #head} and {@link #stackTail}.
		 */
		private ImmutableStack ( T head, ImmutableStack<T> tail ) {
			this.head = head ;
			this.stackTail = tail ;
		}
		
		/**
		 * Checks if this {@link com.tsorman.immutable_queue.ImmutableQueue.ImmutableStack} is empty. 
		 * @return true if both the {@link #head} and the {@link #stackTail} are empty.
		 */
		public boolean isEmpty ( ) {
			return this.head == null && this.stackTail == null ;
		}
		
		/**
		 * Returns the head of this {@link com.tsorman.immutable_queue.ImmutableQueue.ImmutableStack}
		 * @return the head of the Stack
		 */
		public T head() 
		{
			return this.head () ;
		}
		
		/**
		 * Creates a new {@link com.tsorman.immutable_queue.ImmutableQueue.ImmutableStack} with the added parameter.
		 * @param t the parameter to be added to the new {@link com.tsorman.immutable_queue.ImmutableQueue.ImmutableStack}.
		 * @return a new {@link com.tsorman.immutable_queue.ImmutableQueue.ImmutableStack} with the passed parameter.
		 */
		public ImmutableStack<T> push (T t) {
			if ( t == null )
				throw new IllegalArgumentException() ;
			
			return new ImmutableStack<T>(t, this) ;
		}
		
		/**
		 * Returns a new {@link com.tsorman.immutable_queue.ImmutableQueue.ImmutableStack} that is a copy of the {@link #stackTail} of this one. 
		 * @return a copy of the {@link #stackTail}
		 */
		public ImmutableStack<T> pop(){
			return new ImmutableStack<T>(stackTail.head, stackTail.stackTail )  ;
		}
		
		/**
		 * Convenience method that returns a copy of this {@link com.tsorman.immutable_queue.ImmutableQueue.ImmutableStack} reversed.
		 * @return a reversed copy of this {@link com.tsorman.immutable_queue.ImmutableQueue}
		 */
		public ImmutableStack<T> reverse (){
			ImmutableStack<T> reversedStack = new ImmutableStack<T>();
			ImmutableStack<T> tempStack = this;
            while(!tempStack.isEmpty()){
                reversedStack = reversedStack.push(tempStack.head);
                tempStack = tempStack.stackTail;
            }
            return reversedStack;
		}
		
		
		
	}

