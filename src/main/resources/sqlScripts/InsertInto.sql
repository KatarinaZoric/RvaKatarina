
insert into kredit 
values (nextval('kredit_seq'), 'Stambeni kredit', 'Sk', 'Dugorocni, namenski kredit, sa rokom otplate duziim od 12 meseci'),
       (nextval('kredit_seq'), 'Gotovinski kredit', 'Gk', 'Kratkorocni, nenamenski kredit, sa rokom otplate kracim od 12 meseci'),
       (nextval('kredit_seq'), 'Auto kredit', 'Ak', 'Dugorocni, namenski kredit, sa rokom otplate duzim od 12 meseci');
	   
insert into tip_racuna
values (nextval('tip_racuna_seq'), 'tekuci', 'tek1', 'racun za izvrsavanje platnih transakcija'),
       (nextval('tip_racuna_seq'), 'ziro', 'z1', 'racun za honorarna primanja'),
	   (nextval('tip_racuna_seq'), 'namenski', 'nam1', 'racun otvoren sa posebnom namenom'),
	   (nextval('tip_racuna_seq'), 'devizni', 'd1', 'racun za licne uplate i prilive iz inostranstva');
	  
insert into klijent
values (nextval('klijent_seq'), 'Katarina', 'Zoric', 0506000, 1),
       (nextval('klijent_seq'), 'Teodora', 'Stankovic', 0506000, 3),
	   (nextval('klijent_seq'), 'Andjela', 'Pejin', 1011000, 2),
	   (nextval('klijent_seq'), 'Nikolina', 'Dragojevic', 0506000, 2);
	  
	  
insert into racun 
values (nextval('racun_seq'), 'racun1', 'r1', 'prvi otvoren racun', 1, 2),
       (nextval('racun_seq'), 'racun2', 'r2', 'drugi otvoren racun', 2, 1),
	   (nextval('racun_seq'), 'racun3', 'r3', 'treci otvoren racun', 3, 3);

