SELECT Concat(Year(CONVERT(DATE, kontakt_ts)), 
       Format(CONVERT(DATE, kontakt_ts), 'MM'), 
              Format(CONVERT(DATE, kontakt_ts), 'dd')) AS 'data', 
       Sum(Count(kontakt_id)) 
         OVER( 
           ORDER BY kontakt_ts)                        AS 'sukcesy', 
       NULL                                            AS 'utraty', 
       NULL                                            AS 'do_ponowienia' 
INTO   [f_docieralnosc]
FROM   [statuses] 
WHERE  [status] = 'zainteresowany' 
GROUP  BY kontakt_id, 
          kontakt_ts 
UNION ALL 
SELECT Concat(Year(CONVERT(DATE, kontakt_ts)), 
       Format(CONVERT(DATE, kontakt_ts), 'MM'), 
              Format(CONVERT(DATE, kontakt_ts), 'dd')) AS 'data', 
       NULL                                            AS 'sukcesy', 
       Sum(Count(kontakt_id)) 
         OVER( 
           ORDER BY kontakt_ts)                        AS 'utraty', 
       NULL                                            AS 'do_ponowienia' 
FROM   [statuses] 
WHERE  [status] = 'niezainteresowany' 
GROUP  BY kontakt_id, 
          kontakt_ts 
UNION ALL 
SELECT Concat(Year(CONVERT(DATE, kontakt_ts)), 
       Format(CONVERT(DATE, kontakt_ts), 'MM'), 
              Format(CONVERT(DATE, kontakt_ts), 'dd')) AS 'data', 
       NULL                                            AS 'sukcesy', 
       NULL                                            AS 'utraty', 
       Sum(Count(kontakt_id)) 
         OVER( 
           ORDER BY kontakt_ts)                        AS 'do_ponowienia' 
FROM   [statuses] 
WHERE  [status] = 'poczta_glosowa' 
        OR [status] = 'nie_ma_w_domu' 
GROUP  BY kontakt_id, 
          kontakt_ts 
ORDER  BY [data] 