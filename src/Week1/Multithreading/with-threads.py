import os
import re
from threading import Thread
from time import time


class TestIt(Thread):
    def __init__(self, ip):
        Thread.__init__(self)
        self.ip = ip
        self.status = -1

    def run(self):
        pingaling = os.popen("ping -q -c2 " + self.ip, "r")
        while 1:
            line = pingaling.readline()
            if not line: break
            igot = re.findall(TestIt.lifeline, line)
            if igot:
                self.status = int(igot[0])


TestIt.lifeline = re.compile(r"(\d) received")
report = ("No response", "Partial Response", "Alive")
# print time.ctime()
start = time()
pinglist = []

for host in range(1, 49):
    ip = "129.11.146." + str(host)
    current = TestIt(ip)
    pinglist.append(current)
    current.start()

for pingle in pinglist:
    pingle.join()
    print("Status from ", pingle.ip, "is", report[pingle.status])

stop = time()
elapsed = stop - start
print("Time elapsed", elapsed)
print("done.")
