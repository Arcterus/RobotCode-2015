//PID control 

typedef struct {
    double proportional_gain;
    double integral_gain;
    double derivative_gain;
    double prev_error;
    double int_error;
    double control;
} PID;

void pid_zeroize(PID* pid) {
    pid->prev_error = 0;
    pid->int_error = 0;
}

void pid_init(PID* pid, double K_p, double K_i, double K_d) {
    pid->proportional_gain = K_p;
    pid->integral_gain = K_i;
    pid->derivative_gain = K_d;
}

//dt is the time difference
//get curr_error from atan, distance away from object, etc. 
void pid_update(PID* pid, double curr_error, double dt) {
    double diff;
    
    double p_term;
    double i_term;
    double d_term;

    pid->int_error += (curr_error * dt);
    diff = ((curr_error - pid->prev_error) / dt);

    // scaling
    p_term = (pid->proportional_gain * curr_error);
    i_term = (pid->integral_gain * pid->int_error);
    d_term = (pid->derivative_gain * diff);

    // summation of terms and setting up for next iteration
    pid->control = p_term + i_term + d_term;
    pid->prev_error = curr_error;
}
