package sorting;

public interface Sort {
	public void ascending(int[] arr);
	public void descending(int[] arr);
	default void description() {}
}
