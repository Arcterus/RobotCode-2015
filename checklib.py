import os,sys, subprocess as sub

# A simple python script to check for the existence of the HarkerRoboLib
# and to see whether or not it is on its latest version. Note that the
# project root should be passed as a command line argument in the build.xml
# file of the project.

def checklib():
    print "Checking HarkerRoboLib Status...\n"
    if (sub.call(["cd", os.path.expanduser("~/harkerrobolib")]) == 0): #If the project already exists, check version
        print "HarkerRoboLib Found\n"
        print "Checking HarkerRoboLib Version...\n"
        status = sub.check_output(["git","status","-s"], cwd=os.path.expanduser("~/harkerrobolib")) #Checks for changes between local and repo version
        if (status  == "" or status[0] == "?"): #?? denotes an untracked change
            print "Local Version is up-to-date\n"
        else:
            print "Local Version does not match Repository Version\n"
            print "Attempting to merge changes...\n"
            os.system("cd " + os.path.expanduser("~") + ";git pull")
    else: #Project does not exist, clone it from GitHub
        print "No HarkerRoboLib Version Detected\n"
        print "Cloning HarkerRoboLib to Home (~) Directory...\n"
        os.system("cd " + os.path.expanduser("~") + ";git clone https://github.com/HarkerRobo/harkerrobolib")
        print "Cloning Complete\n"
    print "HarkerRoboLib Check Passed! :)\n"
checklib()
