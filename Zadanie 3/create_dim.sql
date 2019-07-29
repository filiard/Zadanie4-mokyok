INSERT INTO [dbo].[d_data] 
SELECT DISTINCT
	CONCAT(YEAR(kontakt_ts), FORMAT(kontakt_ts, 'MM'), FORMAT(kontakt_ts, 'dd')), --data w tym formacie jest unikalna liczb¹
	YEAR(kontakt_ts) as "Year",
	MONTH(kontakt_ts) as "Month",
	DAY(kontakt_ts) as "Day"
FROM [dbo].[Statuses]