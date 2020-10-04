import java.util.ArrayList;

public class PatientQueue {
    private Patient[] array;
    int front;
    int end;
    int last;
    int size;
    int c;

    //constructor: set variables
    //capacity = initial capacity of array
    public PatientQueue(int capacity) {
        c = capacity;
        array = new Patient[c];
        int end = 0;
        int front = 0;
        int size = 0;
        //TO BE COMPLETED
    }

    //insert Patient p into queue
    //return the final index at which the patient is stored
    //return -1 if the patient could not be inserted
    public int insert(Patient p) {
        if (size == c) {
            return -1;
        }

        array[size] = p;

        size++;
        int n = size;
        buildHeap(array, n);


        return size - 1;

    }

    //remove and return the patient with the highest urgency level
    //if there are multiple patients with the same urgency level,
    //return the one who arrived first
    public Patient delMax() {
        if (isEmpty()) {
            return null;
        }
        int maxUrgency = array[0].urgency();
        long maxTime = array[0].time_in();
        Patient max = array[0];
        for (int i = 1; i < size; i++) {
            if (array[i].urgency() == maxUrgency) {
                if (array[i].time_in() < maxTime) {
                    max = array[i];
                    maxTime = max.time_in();
                }
            }
        }
        Patient top = max;
        for (int i = 0; i < size; i++) {
            if (array[i] == max) {
                array[i] = null;
                break;
            }

        }

        array = resized(array);
        size --;
        int n = size;
        buildHeap(array,n);

        return top;
        //TO BE COMPLETED
    }

    //return but do not remove the first patient in the queue
    public Patient getMax() {
        Patient max = array[0];
        return max;
        //TO BE COMPLETED
    }

    //return the number of patients currently in the queue
    public int size() {
        return size;
        //TO BE COMPLETED
    }

    //return true if the queue is empty; false else
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
        //TO BE COMPLETED
    }

    //used for testing underlying data structure
    public Patient[] getArray() {
        return array;
    }

    public void heapify(Patient[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && arr[left].urgency() > arr[largest].urgency()) {
            largest = left;
        }
        if (right < n && arr[right].urgency() > arr[largest].urgency()) {
            largest = right;
        }
        if (largest != i) {
            Patient temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            heapify(arr, n, largest);
        }
    }

    public void  buildHeap(Patient[] arr, int n) {
        int start = (n/2) - 1;
        for (int i = start; i >= 0; i--) {
            heapify(arr, n, i);
        }
    }

    public Patient[] resized(Patient[] arr) {
        ArrayList<Patient> patientList = new ArrayList<Patient>();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                patientList.add(arr[i]);
            }
        }

        Patient[] array = new Patient[c];

        for (int i = 0; i < patientList.size(); i++) {
            array[i] = patientList.get(i);
        }

        return array;


    }
}
