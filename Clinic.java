public class Clinic {
    private NewPatientQueue pq;
    private int er_threshold;
    private int capacity;
    private int processed = 0;
    private int seenByDoctor = 0;
    private int sentToER = 0;
    private int walkedOut = 0;

    public Clinic(int cap, int er_threshold) {
        pq = new NewPatientQueue(cap);
        capacity = cap;
        this.er_threshold = er_threshold;
        
    }

    public int er_threshold() {
        return this.er_threshold;
    }

    public int capacity() {
        return this.capacity;
    }


    public String process(String name, int urgency) {
        Patient p = new Patient(name, urgency, processed);
         processed++;

        if (urgency > er_threshold) {
            sendToER(p);

            return null;
        }
        if ((pq.insert(p) != -1)) {
            return p.name();
        } else {
            if (p.compareTo(pq.getMax()) > 0) {
                sendToER(p);
                return null;
            } else {
                Patient max = pq.delMax();
                sendToER(max);
                pq.insert(p);
                return max.name();
            }
        }



    }

    /*a doctor is available--send the patient with
     *highest urgency to be seen; return the name
     *of the Patient or null if the queue is empty*/
    public String seeNext() {
        if (!pq.isEmpty()) {
            Patient del = pq.delMax();
            seeDoctor(del);
            return del.name();
        }
        return null;
        
    }

    /*Patient experiences an emergency, raising their
     *urgency level; if the urgency level exceeds the
     *er_threshold, send them directly to the emergency room;
     *else update their urgency status in the queue;
     *return true if the Patient is removed from the queue
     *and false otherwise*/
    public boolean handle_emergency(String name, int urgency) {
        Patient p = pq.ph.get(name);
        if (urgency > er_threshold) {
            sendToER(p);
            return true;
        } else {
            pq.update(name,urgency);
            return false;
        }

    }

    /*Patient decides to walk out
     *remove them from the queue*/
    public void walk_out(String name) {
        pq.remove(name);
        //TO BE COMPLETED
        walkedOut++;
    }

    /*Indicates that Patient p has been sent to the ER*/
    private void sendToER(Patient p) {
        System.out.println("Patient " + p + " sent to ER.");
        sentToER++;
    }
    /*Indicates that a patient is being seen by a doctor*/
    private void seeDoctor(Patient p) {
        System.out.println("Patient " + p + " is seeing a doctor.");
        seenByDoctor++;
    }

    public int processed() {
        return processed;
    }

    public int sentToER() {
        return sentToER;
    }

    public int seenByDoctor() {
        return seenByDoctor;
    }

    public int walkedOut() {
        return walkedOut;
    }
}
