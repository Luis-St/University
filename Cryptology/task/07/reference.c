/* reference.c  -  CPU-Referenz & Verifier fuer die Toy-Blockchain (Aufgabe 7)
 *
 * Identische Nachrichten-Konstruktion wie der GPU-Miner (miner.cu).
 * Zweck: SHA256 verifizieren, Format pruefen, kleine k auf der CPU minen.
 *
 * Bauen:  gcc -O3 -pthread reference.c -o reference
 * Lauf :  ./reference            (Self-Test + Chain mit K aus Config)
 *
 * Nachrichtenformat (ASCII), das gehasht wird:
 *   MESSAGE = IV(64 hex) + TX + "(8)" + NONCE(32 hex)
 *   TX      = "(1)"TXNUM_HEX "(2)"SHA1(NAME) "(3)"UNIXTIME
 *             "(4)"SHA1(RECIPIENT) "(5)"AMOUNT "(6)"LEN "(7)"
 *   LEN     = Zeichenlaenge der Felder (1)..(5)  (Transaktionsdaten ohne Laenge/Nonce)
 *   IV      = Block 1: 64x '0'; danach: Hex(Hash des Vorblocks)
 *   k Punkte, wenn die ersten K Hex-Nibbles von SHA256(MESSAGE) == 0
 */
#include <stdio.h>
#include <stdint.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>

/* ====================== CONFIG (anpassen) ====================== */
#define MATRIKELNUMMER 272056ULL        /* dezimal; wird als Hex-Transaktionsnr ausgegeben */
#define NAME           "Luis Noah Staudt"  /* Vor- und Nachname -> SHA1 */
#define K_NIBBLES      6                /* hier klein zum Testen; GPU macht 10 */
static const char *RECIPIENTS[3] = {"Alice", "Bob", "Carol"};
static const char *AMOUNTS[3]    = {"42", "100", "7"};
/* ============================================================== */

/* ----------------------- SHA-256 ----------------------- */
#define ROTR(x,n) (((x)>>(n))|((x)<<(32-(n))))
#define EP0(x) (ROTR(x,2)^ROTR(x,13)^ROTR(x,22))
#define EP1(x) (ROTR(x,6)^ROTR(x,11)^ROTR(x,25))
#define SIG0(x) (ROTR(x,7)^ROTR(x,18)^((x)>>3))
#define SIG1(x) (ROTR(x,17)^ROTR(x,19)^((x)>>10))
#define CHb(x,y,z) (((x)&(y))^(~(x)&(z)))
#define MAJ(x,y,z) (((x)&(y))^((x)&(z))^((y)&(z)))

static void sha256_transform(uint32_t st[8], const uint8_t b[64]) {
    static const uint32_t Kc[64] = {
        0x428a2f98,0x71374491,0xb5c0fbcf,0xe9b5dba5,0x3956c25b,0x59f111f1,0x923f82a4,0xab1c5ed5,
        0xd807aa98,0x12835b01,0x243185be,0x550c7dc3,0x72be5d74,0x80deb1fe,0x9bdc06a7,0xc19bf174,
        0xe49b69c1,0xefbe4786,0x0fc19dc6,0x240ca1cc,0x2de92c6f,0x4a7484aa,0x5cb0a9dc,0x76f988da,
        0x983e5152,0xa831c66d,0xb00327c8,0xbf597fc7,0xc6e00bf3,0xd5a79147,0x06ca6351,0x14292967,
        0x27b70a85,0x2e1b2138,0x4d2c6dfc,0x53380d13,0x650a7354,0x766a0abb,0x81c2c92e,0x92722c85,
        0xa2bfe8a1,0xa81a664b,0xc24b8b70,0xc76c51a3,0xd192e819,0xd6990624,0xf40e3585,0x106aa070,
        0x19a4c116,0x1e376c08,0x2748774c,0x34b0bcb5,0x391c0cb3,0x4ed8aa4a,0x5b9cca4f,0x682e6ff3,
        0x748f82ee,0x78a5636f,0x84c87814,0x8cc70208,0x90befffa,0xa4506ceb,0xbef9a3f7,0xc67178f2};
    uint32_t w[64];
    for (int i=0;i<16;i++) w[i]=(b[i*4]<<24)|(b[i*4+1]<<16)|(b[i*4+2]<<8)|b[i*4+3];
    for (int i=16;i<64;i++) w[i]=SIG1(w[i-2])+w[i-7]+SIG0(w[i-15])+w[i-16];
    uint32_t a=st[0],bb=st[1],c=st[2],d=st[3],e=st[4],f=st[5],g=st[6],h=st[7];
    for (int i=0;i<64;i++){
        uint32_t t1=h+EP1(e)+CHb(e,f,g)+Kc[i]+w[i];
        uint32_t t2=EP0(a)+MAJ(a,bb,c);
        h=g;g=f;f=e;e=d+t1;d=c;c=bb;bb=a;a=t1+t2;
    }
    st[0]+=a;st[1]+=bb;st[2]+=c;st[3]+=d;st[4]+=e;st[5]+=f;st[6]+=g;st[7]+=h;
}

static void sha256(const uint8_t *msg, size_t len, uint8_t out[32]) {
    uint32_t st[8]={0x6a09e667,0xbb67ae85,0x3c6ef372,0xa54ff53a,0x510e527f,0x9b05688c,0x1f83d9ab,0x5be0cd19};
    uint8_t blk[64]; size_t i=0;
    while (len-i>=64){ memcpy(blk,msg+i,64); sha256_transform(st,blk); i+=64; }
    size_t rem=len-i; memcpy(blk,msg+i,rem); blk[rem]=0x80;
    if (rem>=56){ memset(blk+rem+1,0,64-rem-1); sha256_transform(st,blk); memset(blk,0,56); }
    else memset(blk+rem+1,0,56-rem-1);
    uint64_t bits=(uint64_t)len*8;
    for (int j=0;j<8;j++) blk[63-j]=(bits>>(8*j))&0xff;
    sha256_transform(st,blk);
    for (int j=0;j<8;j++){ out[j*4]=st[j]>>24; out[j*4+1]=st[j]>>16; out[j*4+2]=st[j]>>8; out[j*4+3]=st[j]; }
}

/* ----------------------- SHA-1 (fuer Namen) ----------------------- */
static void sha1(const uint8_t *msg, size_t len, uint8_t out[20]) {
    uint32_t h0=0x67452301,h1=0xEFCDAB89,h2=0x98BADCFE,h3=0x10325476,h4=0xC3D2E1F0;
    size_t ml=len*8;
    size_t total=((len+8)/64+1)*64;
    uint8_t *m=calloc(total,1); memcpy(m,msg,len); m[len]=0x80;
    for (int j=0;j<8;j++) m[total-1-j]=(ml>>(8*j))&0xff;
    for (size_t off=0; off<total; off+=64){
        uint32_t w[80];
        for (int i=0;i<16;i++) w[i]=(m[off+i*4]<<24)|(m[off+i*4+1]<<16)|(m[off+i*4+2]<<8)|m[off+i*4+3];
        for (int i=16;i<80;i++){ uint32_t v=w[i-3]^w[i-8]^w[i-14]^w[i-16]; w[i]=(v<<1)|(v>>31); }
        uint32_t a=h0,b=h1,c=h2,d=h3,e=h4;
        for (int i=0;i<80;i++){
            uint32_t f,k;
            if (i<20){f=(b&c)|(~b&d);k=0x5A827999;}
            else if (i<40){f=b^c^d;k=0x6ED9EBA1;}
            else if (i<60){f=(b&c)|(b&d)|(c&d);k=0x8F1BBCDC;}
            else {f=b^c^d;k=0xCA62C1D6;}
            uint32_t t=((a<<5)|(a>>27))+f+e+k+w[i];
            e=d;d=c;c=(b<<30)|(b>>2);b=a;a=t;
        }
        h0+=a;h1+=b;h2+=c;h3+=d;h4+=e;
    }
    free(m);
    uint32_t hh[5]={h0,h1,h2,h3,h4};
    for (int j=0;j<5;j++){ out[j*4]=hh[j]>>24; out[j*4+1]=hh[j]>>16; out[j*4+2]=hh[j]>>8; out[j*4+3]=hh[j]; }
}

static void tohex_up(const uint8_t *b, int n, char *out){ const char*h="0123456789ABCDEF"; for(int i=0;i<n;i++){out[i*2]=h[b[i]>>4];out[i*2+1]=h[b[i]&0xf];} out[n*2]=0; }

/* fuehrende 0-Nibbles eines 32-Byte Hashs */
static int leading_zero_nibbles(const uint8_t *d){
    int n=0; for(int i=0;i<32;i++){ if((d[i]>>4)==0)n++; else break; if((d[i]&0xf)==0)n++; else break; } return n;
}

/* TX-String (1)..(7) bauen */
static void build_tx(char *tx, unsigned long long txnum, const char *name_sha1_hex,
                     long unixtime, const char *recip_sha1_hex, const char *amount) {
    char head[512];
    int len_fields = snprintf(head, sizeof head, "(1)%llX(2)%s(3)%ld(4)%s(5)%s",
                              txnum, name_sha1_hex, unixtime, recip_sha1_hex, amount);
    sprintf(tx, "%s(6)%d(7)", head, len_fields);
}

int main(void){
    /* ---- Self-Test SHA256("abc") ---- */
    uint8_t t[32]; sha256((const uint8_t*)"abc",3,t); char th[65]; tohex_up(t,32,th);
    printf("SHA256(\"abc\") = %s\n", th);
    printf("expected      = BA7816BF8F01CFEA414140DE5DAE2223B00361A396177A9CB410FF61F20015AD\n\n");

    /* ---- Namens-Hashes ---- */
    uint8_t nh[20]; sha1((const uint8_t*)NAME, strlen(NAME), nh); char name_hex[41]; tohex_up(nh,20,name_hex);
    printf("Name: \"%s\"  ->  SHA1 = %s\n", NAME, name_hex);
    char recip_hex[3][41];
    for (int i=0;i<3;i++){ uint8_t r[20]; sha1((const uint8_t*)RECIPIENTS[i],strlen(RECIPIENTS[i]),r); tohex_up(r,20,recip_hex[i]); }

    long now = time(NULL);
    printf("Unixzeit: %ld\nZiel: K=%d fuehrende Null-Nibbles\n\n", now, K_NIBBLES);

    /* ---- Chain: 3 Bloecke ---- */
    char iv[65]; memset(iv,'0',64); iv[64]=0;   /* Block1 IV = ALLZERO */
    for (int b=0;b<3;b++){
        char tx[1024];
        build_tx(tx, MATRIKELNUMMER+b, name_hex, now, recip_hex[b], AMOUNTS[b]);
        char prefix[2048];
        int plen = snprintf(prefix, sizeof prefix, "%s%s(8)", iv, tx);  /* IV+TX+"(8)" */

        /* CPU-Mining: 32-hex Nonce = zufaelliger 64-bit High-Teil + 64-bit Zaehler */
        uint64_t high = ((uint64_t)rand()<<32)^rand()^((uint64_t)now<<13)^(b*0x9e3779b97f4a7c15ULL);
        uint8_t dig[32]; char msg[2200]; char nonce[33]; int found=0;
        for (uint64_t ctr=0; !found; ctr++){
            sprintf(nonce, "%016llx%016llx", (unsigned long long)high, (unsigned long long)ctr);
            int mlen = sprintf(msg, "%s%s", prefix, nonce);
            sha256((const uint8_t*)msg, mlen, dig);
            if (leading_zero_nibbles(dig) >= K_NIBBLES) found=1;
        }
        char dhex[65]; tohex_up(dig,32,dhex);
        printf("===== BLOCK %d  (Empfaenger: %s) =====\n", b+1, RECIPIENTS[b]);
        printf("IV   : %s\n", iv);
        printf("TX   : %s\n", tx);
        printf("NONCE: (8)%s\n", nonce);
        printf("HASH : %s   (%d Null-Nibbles)\n\n", dhex, leading_zero_nibbles(dig));
        memcpy(iv, dhex, 65);   /* naechster IV = Hash dieses Blocks */
    }
    return 0;
}
