CREATE FULLTEXT INDEX ft_index ON items (title,description,keywords) WITH PARSER mecab;
