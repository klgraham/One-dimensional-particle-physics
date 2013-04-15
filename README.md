# One-dimensional Particle Physics and BubbleSort
I recently perused [Donald Knuth's website][1] and saw that he once had a lecture called ["Constructing bubblesort at random: one-dimensional particle physics"][2]. As a physicist and software engineer, I naturally found this interesting. So this short bit of code takes a chain of 1s and 0s, as specified below, and sorts them at random. It also tells you how many passes it takes. Also, I'm not yet clear on how this relates to physics, at least not in a detailed sense. I'll have to think about that some more.

Here's a note from one of the papers Knuth linked to:

*Start with infinitely many 1s followed by infinitely many 0s; then randomly interchange adjacent elements that are out of order.*

## 1D physics

The physics connection becomes a bit more clear if you imagine a 1D lattice, where the 1s are particles and the 0s are vacancies. Now, pick any site k and ask how many particles are to the right of site k. Call the result $R_k$. The number of 0s to the left of site k is $L_k$. Note that if $k=N/2$, then $R_k=L_k$.

It might be interesting to track how $R_k$ and $L_k$ change with "time". Here, time means the number of random swaps needed to sort the list. For a long lattice, it might also be interesting to compute the distribution of 1s or 0s as a function of lattice site index. Then we could watch it change from all 1s on the left to all 1s on the right. Might look like a Heaviside Step Function inverting bit by bit.

[1]: http://www-cs-faculty.stanford.edu/~knuth/musings.html
[2]: http://myvideos.stanford.edu/player/slplayer.aspx?coll=ea60314a-53b3-4be2-8552-dcf190ca0c0b&co=b276896a-4cc1-4d64-953c-7545af811f9c&o=true&AspxAutoDetectCookieSupport=1
