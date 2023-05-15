-- 코드를 입력하세요
WITH RECURSIVE HOURS (HOUR) AS (
    SELECT 0
    UNION ALL
    SELECT HOUR + 1
    FROM HOURS
    WHERE HOUR < 23
)
SELECT HOUR, COUNT(DATETIME) AS COUNT
FROM HOURS H LEFT JOIN ANIMAL_OUTS AO ON H.HOUR = HOUR(AO.DATETIME)
GROUP BY HOUR
ORDER BY HOUR