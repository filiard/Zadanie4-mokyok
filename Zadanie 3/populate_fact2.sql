SELECT t1.*, 
       t2.*, 
       t3.* 
INTO   [f_docieralnosc3] 
FROM   (SELECT CONVERT(DATE, kontakt_ts) AS 'data1', 
               Sum(Count(kontakt_id)) 
                 OVER( 
                   ORDER BY kontakt_ts)  AS 'do_ponowienia' 
        FROM   [statuses] 
        WHERE  [status] = 'poczta_glosowa' 
                OR [status] = 'nie_ma_w_domu' 
        GROUP  BY kontakt_id, 
                  klient_id, 
                  pracownik_id, 
                  [status], 
                  kontakt_ts) t1 
       FULL OUTER JOIN (SELECT CONVERT(DATE, kontakt_ts) AS 'data2', 
                               Sum(Count(kontakt_id)) 
                                 OVER( 
                                   ORDER BY kontakt_ts)  AS 'utraty' 
                        FROM   [statuses] 
                        WHERE  [status] = 'niezainteresowany' 
                        GROUP  BY kontakt_id, 
                                  klient_id, 
                                  pracownik_id, 
                                  [status], 
                                  kontakt_ts) t2 
                    ON t1.[data1] = t2.[data2] 
       FULL OUTER JOIN (SELECT CONVERT(DATE, kontakt_ts) AS 'data3', 
                               Sum(Count(kontakt_id)) 
                                 OVER( 
                                   ORDER BY kontakt_ts)  AS 'sukcesy' 
                        FROM   [statuses] 
                        WHERE  [status] = 'zainteresowany' 
                        GROUP  BY kontakt_id, 
                                  klient_id, 
                                  pracownik_id, 
                                  [status], 
                                  kontakt_ts) t3 
                    ON t1.[data1] = t3.[data3] 
ORDER  BY t1.[data1], 
          t2.[data2], 
          t3.[data3]