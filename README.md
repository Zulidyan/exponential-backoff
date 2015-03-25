# Exponential Backoff
A simulation of the exponential backoff algorithm from Ethernet protocol in which a station requests to send a block of data. If more than one station attempts to transmit at the same time, a collision occurs and will attempt to send at a different time, waiting at a random slot time selected from 0 to 2^c, where c is the number of the station's collisions.
