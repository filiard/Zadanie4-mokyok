SELECT t1.[klient_id], 
       [status] 
FROM   [dbo].[statuses] t1 
       INNER JOIN (SELECT * 
                   FROM  (SELECT[klient_id], 
                                Count([kontakt_id]) AS "kontakty z klientem", 
                                Max(kontakt_ts)     AS "ostatni kontakt" 
                          FROM   [dbo].[statuses] 
                          GROUP  BY [klient_id]) AS tab 
                   WHERE  tab.[kontakty z klientem] > 2) t2 
               ON t1.[kontakt_ts] = t2.[ostatni kontakt] 