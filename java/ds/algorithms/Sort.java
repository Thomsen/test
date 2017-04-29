public class Sort {

    public static void main(String[] args) {

        //int[] arr = new int[] {23, 22, 11, 2, 3, 232, 211, 32, 12};
        int[] arr = new int[] {30, 20, 10, 9, 8, 7, 6};

        Sort sort = new Sort();

        print("arr: ", arr);

        print("insertion sort: ", sort.insertion(arr));

        print("bubble sort: ", sort.bubble(arr));

        print("selection sort: ", sort.selection(arr));


        print("merge sort: ", sort.mergeSort(arr));

    }

    public int[] insertion(int[] arr) {
        int[] a = arr.clone();
        int n = a.length;
        int count = 0;

        for (int j=1; j<n; j++) {
            // int key = a[j];
            // int i = j-1;
            // while (i>=0 && key < a[i]) {
            //     a[i+1] = a[i];
            //     i--;
            //     count ++;
            // }
            // a[i+1] = key;
            for (int i=j-1; i>=0 && a[i+1]<a[i]; i--) {
                int tmp = a[i+1];
                a[i+1] = a[i];
                a[i] = tmp;
                count ++;
            }
        }
        System.out.println("insertion step count: " + count);
        return a;
    }

    public int[] bubble(int[] arr) {
        int[] a = arr.clone();
        int n = a.length;
        int count = 0;
        for (int i=0; i<n; i++) {
            for (int j=n-1; j>i; j--) {
                if (a[j-1] > a[j]) {
                    int tmp = a[j-1];
                    a[j-1] = a[j];
                    a[j] = tmp;
                }
                count ++;
            }
        }

        System.out.println("bubble step count: " + count);
        return a;
    }

    public int[] selection(int[] arr) {
        int[] a = arr.clone();
        int n = a.length;
        int count = 0;
        for (int i=0; i<n; i++) {
            int min = a[i];
            int minIndex = i;
            for (int j=i+1; j<n; j++) {
                if (min > a[j]) {
                    min = a[j];
                    minIndex = j;
                }
                count ++ ;
            }

            int tmp = a[i];
            a[i] = min;
            a[minIndex] = tmp;
        }
        System.out.println("selection step count: " + count);
        return a;
    }

    public int[] mergeSort(int[] arr) {
        int[] a = arr.clone();
        int[] helper = new int[arr.length];
        mergeSort(a, helper, 0, arr.length - 1);
        print("merge sort helper: ", helper);
        return a;
    }

    public void mergeSort(int[] arr, int[] a, int low, int high) {
        if (low < high) {
            int middle = (low + high) / 2;
            mergeSort(arr, a, low, middle);
            mergeSort(arr, a, middle+1, high);

            merge(arr, a, low, middle, high); // 0, 0, 1
        }
    }

    private void merge(int[] arr, int[] helper, int low, int middle, int high) {
        for (int i=low; i<=high; i++) {
            helper[i] = arr[i];
        }

        int left = low;
        int right = middle+1;
        int current = low;

        System.out.println("left " + left);
        System.out.println("middle " + middle);
        System.out.println("high " + high);
        while(left <= middle && right <= high) {
            if (helper[left] <= helper[right]) {
                arr[current] = helper[left];
                left ++;
            } else {
                arr[current] = helper[right++];
            }

            current ++;
            print("merge step arr: ", arr);
        }

        int remaining = middle - left;
        System.out.println("current " + current);
        System.out.println("remaining " + remaining);
        for (int i=0; i<=remaining; i++) {
            arr[current+i] = helper[left+i];
        }
        print("merge arr: ", arr);
        print("merge helper arr: ", helper);
    }

    private static void print(String desc, int[] arr) {
        int n = arr.length;
        System.out.print(desc);
        for (int i=0; i<n; i++) {
            System.out.print(arr[i] + "  ");
        }
        System.out.println("");
    }

}
