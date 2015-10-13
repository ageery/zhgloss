create table radical (
   radical_id int primary key,
   radical_char text not null,
   simplified_radical_char text,
   stroke_count int not null,
   pinyin text not null,
   tone int not null,
   meaning text not null
);
 
create index ix_radical_radical_char on radical(radical_char);
create index ix_radical_stroke_count on radical(stroke_count);
 
copy radical from stdin csv;
1,一,,1,yi,1,one
2,丨,,1,gun,3,line
3,丶,,1,zhu,3,dot
4,丿,,1,pie,3,slash
5,乙,,1,yi,3,second
6,亅,,1,jue,2,hook
7,二,,2,er,4,two
8,亠,,2,tou,2,lid
9,人,,2,ren,2,man
10,儿,,2,er,2,"son, legs"
11,入,,2,ru,4,enter
12,八,,2,ba,1,eight
13,冂,,2,jiong,1,(down box)
14,冖,,2,mi,4,cover
15,冫,,2,bing,1,ice
16,几,,2,ji,1,table
17,凵,,2,qu,1,(open box)
18,刀,,2,dao,1,knife
19,力,,2,li,4,power
20,勹,,2,bao,1,wrap
21,匕,,2,bi,3,spoon
22,匚,,2,fang,1,(right open box)
23,匸,,2,xi,3,hiding enclosure
24,十,,2,shi,2,ten
25,卜,,2,bu,3,divination
26,卩,,2,jie,2,seal (device)
27,厂,(pr. chǎng),2,han,4,cliff
28,厶,,2,si,1,private
29,又,,2,you,4,again
30,口,,3,kou,3,mouth
31,囗,,3,wei,2,enclosure
32,土,,3,tu,3,earth
33,士,,3,shi,4,scholar
34,夂,,3,zhi,3,go
35,夊,,3,sui,1,go slowly
36,夕,,3,xi,1,evening
37,大,,3,da,4,big
38,女,,3,nü,3,woman
39,子,,3,zi,3,child
40,宀,,3,mian,2,roof
41,寸,,3,cun,4,inch
42,小,,3,xiao,3,small
43,尢,,3,wang,1,lame
44,尸,,3,shi,1,corpse
45,屮,,3,che,4,sprout
46,山,,3,shan,1,mountain
47,巛,,3,chuan,1,river
48,工,,3,gong,1,work
49,己,,3,ji,3,oneself
50,巾,,3,jin,1,turban
51,干,,3,gan,1,dry
52,幺,,3,yao,1,short thread
53,广,,3,guang,3,(dotted cliff)
54,廴,,3,yin,3,long stride
55,廾,,3,gong,3,(two hands)
56,弋,,3,yi,4,shoot
57,弓,,3,gong,1,bow
58,彐,,3,ji,4,snout
59,彡,,3,shan,1,bristle
60,彳,,3,chi,4,step
61,心,,4,xin,1,heart
62,戈,,4,ge,1,halberd
63,戶,,4,hu,4,door
64,手,,4,shou,3,hand
65,支,,4,zhi,1,branch
66,攴,,4,pu,1,"rap, tap"
67,文,,4,wen,2,script
68,斗,,4,dou,3,dipper
69,斤,,4,jin,1,axe
70,方,,4,fang,1,square
71,无,,4,wu,2,not
72,日,,4,ri,4,sun
73,曰,,4,yue,1,say
74,月,,4,yue,4,moon
75,木,,4,mu,4,tree
76,欠,,4,qian,4,lack
77,止,,4,zhi,3,stop
78,歹,,4,dai,3,death
79,殳,,4,shu,1,weapon
80,毋,,4,wu,2,do not
81,比,,4,bi,3,compare
82,毛,,4,mao,2,fur
83,氏,,4,shi,4,clan
84,气,,4,qi,4,steam
85,水,,4,shui,3,water
86,火,,4,huo,3,fire
87,爪,,4,zhao,3,claw
88,父,,4,fu,4,father
89,爻,,4,yao,2,(double x)
90,爿,,4,qiang,2,(half tree trunk)
91,片,,4,pian,4,slice
92,牙,,4,ya,2,fang
93,牛,,4,niu,2,cow
94,犬,,4,quan,3,dog
95,玄,,5,xuan,2,profound
96,玉,,5,yu,4,jade
97,瓜,,5,gua,1,melon
98,瓦,,5,wa,3,tile
99,甘,,5,gan,1,sweet
100,生,,5,sheng,1,life
101,用,,5,yong,4,use
102,田,,5,tian,2,field
103,疋,,5,pi,3,bolt of cloth
104,疒,,5,ne,4,sickness
105,癶,,5,bo,1,"dotted tent, legs"
106,白,,5,bai,2,white
107,皮,,5,pi,2,skin
108,皿,,5,min,3,dish
109,目,,5,mu,4,eye
110,矛,,5,mao,2,spear
111,矢,,5,shi,3,arrow
112,石,,5,shi,2,stone
113,示,,5,shi,4,spirit
114,禸,,5,rou,2,track
115,禾,,5,he,2,grain
116,穴,,5,xue,2,cave
117,立,,5,li,4,stand
118,竹,,6,zhu,2,bamboo
119,米,,6,mi,3,rice
120,糸,纟,6,mi,4,silk
121,缶,,6,fou,3,jar
122,网,,6,wang,3,net
123,羊,,6,yang,2,sheep
124,羽,,6,yu,3,feather
125,老,,6,lao,3,old
126,而,,6,er,2,and
127,耒,,6,lei,3,plow
128,耳,,6,er,3,ear
129,聿,,6,yu,4,brush
130,肉,,6,rou,4,meat
131,臣,,6,chen,2,minister
132,自,,6,zi,4,self
133,至,,6,zhi,4,arrive
134,臼,,6,jiu,4,mortar
135,舌,,6,she,2,tongue
136,舛,,6,chuan,3,oppose
137,舟,,6,zhou,1,boat
138,艮,,6,gen,1,stopping
139,色,,6,se,4,color
140,艸,,6,cao,3,grass
141,虍,,6,hu,1,tiger
142,虫,(pr. chóng),6,hui,3,insect
143,血,,6,xue,4,blood
144,行,,6,xing,2,walk enclosure
145,衣,,6,yi,1,clothes
146,襾,,6,xi,1,west
147,見,见,7,jian,4,see
148,角,,7,jiao,3,horn
149,言,讠,7,yan,2,speech
150,谷,,7,gu,3,valley
151,豆,,7,dou,4,bean
152,豕,,7,shi,3,pig
153,豸,,7,zhi,4,badger
154,貝,贝,7,bei,4,shell
155,赤,,7,chi,4,red
156,走,,7,zou,3,run
157,足,,7,zu,2,foot
158,身,,7,shen,1,body
159,車,车,7,che,1,cart
160,辛,,7,xin,1,bitter
161,辰,,7,chen,2,morning
162,辵,,7,chuo,4,walk
163,邑,,7,yi,4,city
164,酉,,7,you,3,wine
165,釆,,7,bian,4,distinguish
166,里,,7,li,3,village
167,金,钅,8,jin,1,gold
168,長,长,8,chang,2,long
169,門,门,8,men,2,gate
170,阜,,8,fu,4,mound
171,隶,,8,li,4,slave
172,隹,,8,zhui,1,short-tailed bird
173,雨,,8,yu,3,rain
174,青,,8,qing,1,blue
175,非,,8,fei,1,wrong
176,面,,9,mian,4,face
177,革,,9,ge,2,leather
178,韋,韦,9,wei,2,tanned leather
179,韭,,9,jiu,3,leek
180,音,,9,yin,1,sound
181,頁,页,9,ye,4,leaf
182,風,风,9,feng,1,wind
183,飛,飞,9,fei,1,fly
184,食,饣,9,shi,2,eat
185,首,,9,shou,3,head
186,香,,9,xiang,1,fragrant
187,馬,马,10,ma,3,horse
188,骨,,10,gu,3,bone
189,高,,10,gao,1,tall
190,髟,,10,biao,1,hair
191,鬥,,10,dou,4,fight
192,鬯,,10,chang,4,sacrificial wine
193,鬲,,10,li,4,cauldron
194,鬼,,10,gui,3,ghost
195,魚,鱼,11,yu,2,fish
196,鳥,鸟,11,niao,3,bird
197,鹵,卤,11,lu,3,salt
198,鹿,,11,lu,4,deer
199,麥,麦,11,mai,4,wheat
200,麻,,11,ma,2,hemp
201,黃,,12,huang,2,yellow
202,黍,,12,shu,3,millet
203,黑,,12,hei,1,black
204,黹,,12,zhi,3,embroidery
205,黽,黾,13,min,3,frog
206,鼎,,13,ding,3,tripod
207,鼓,,13,gu,3,drum
208,鼠,,13,shu,3,rat
209,鼻,,14,bi,2,nose
210,齊,齐,14,qi,2,even
211,齒,齿,15,chi,3,tooth
212,龍,龙,16,long,2,dragon
213,龜,龟,16,gui,1,turtle
214,龠,,17,yue,4,flute
\.