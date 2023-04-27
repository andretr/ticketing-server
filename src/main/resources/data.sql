INSERT INTO public.tenants (tenant_id, hidden_tenant, name, tenant_code) VALUES ('ffbb2f98-7bd0-4ebf-8e01-4f908aeb35e9', false, 'Navia', 'NAVIA001');
INSERT INTO public.categories (category_id, tenant_id, default_priority_id, category_name, created, modified) VALUES ('b5557f5a-27e2-4847-a658-21b46b911309', 'ffbb2f98-7bd0-4ebf-8e01-4f908aeb35e9', null, 'NAVIA_CATEGORY', '2023-04-26 15:53:26.190000 +00:00', '2023-04-26 15:53:28.551000 +00:00');
INSERT INTO public.priorities (priority_id, category_id, priority_name, sla_overdue_threshold, sla_overdue_unit, sla_warning_threshold, sla_warning_unit, created, modified) VALUES ('050e3ac8-8dd5-4d1a-a9b9-d62ede250b35', 'b5557f5a-27e2-4847-a658-21b46b911309', 'LOW', 7, 'DAYS', 5, 'DAYS', '2023-04-26 16:05:12.157000 +00:00', '2023-04-26 16:05:12.157000 +00:00');
INSERT INTO public.priorities (priority_id, category_id, priority_name, sla_overdue_threshold, sla_overdue_unit, sla_warning_threshold, sla_warning_unit, created, modified) VALUES ('539864f0-e855-4a83-84b8-d2f4209d3c37', 'b5557f5a-27e2-4847-a658-21b46b911309', 'MEDIUM', 3, 'DAYS', 1, 'DAYS', '2023-04-26 16:05:12.157000 +00:00', '2023-04-26 16:05:12.157000 +00:00');
INSERT INTO public.priorities (priority_id, category_id, priority_name, sla_overdue_threshold, sla_overdue_unit, sla_warning_threshold, sla_warning_unit, created, modified) VALUES ('959c4625-0819-4137-8093-a4dbe049ae15', 'b5557f5a-27e2-4847-a658-21b46b911309', 'HIGH', 12, 'HOURS', 3, 'HOURS', '2023-04-26 16:05:12.157000 +00:00', '2023-04-26 16:05:12.157000 +00:00');
UPDATE public.categories SET category_id = 'b5557f5a-27e2-4847-a658-21b46b911309', tenant_id = 'ffbb2f98-7bd0-4ebf-8e01-4f908aeb35e9', default_priority_id = '539864f0-e855-4a83-84b8-d2f4209d3c37', category_name = 'NAVIA_CATEGORY', created = '2023-04-26 15:53:26.190000 +00:00', modified = '2023-04-26 15:53:28.551000 +00:00' WHERE ctid = '(0,4)';
INSERT INTO public.users (user_id, tenant_id, email, name) VALUES ('266c909f-8193-40bb-95d8-65580b386f86', 'ffbb2f98-7bd0-4ebf-8e01-4f908aeb35e9', 'user1@navia.com', 'user1');
INSERT INTO public.users (user_id, tenant_id, email, name) VALUES ('64891ead-5fd2-4857-a0a9-109c54b1d865', 'ffbb2f98-7bd0-4ebf-8e01-4f908aeb35e9', 'user2@navia.com', 'user2');
INSERT INTO public.users (user_id, tenant_id, email, name) VALUES ('86f9699a-30f9-4010-8144-63ce373586dd', 'ffbb2f98-7bd0-4ebf-8e01-4f908aeb35e9', 'user4@navia.com', 'user4');
INSERT INTO public.users (user_id, tenant_id, email, name) VALUES ('b3e6ad78-9449-4643-95dd-c17cf18435d2', 'ffbb2f98-7bd0-4ebf-8e01-4f908aeb35e9', 'user3@navia.com', 'user3');
INSERT INTO public.user_categories (category_id, user_id) VALUES ('b5557f5a-27e2-4847-a658-21b46b911309', '266c909f-8193-40bb-95d8-65580b386f86');
INSERT INTO public.user_categories (category_id, user_id) VALUES ('b5557f5a-27e2-4847-a658-21b46b911309', '64891ead-5fd2-4857-a0a9-109c54b1d865');
INSERT INTO public.user_categories (category_id, user_id) VALUES ('b5557f5a-27e2-4847-a658-21b46b911309', '86f9699a-30f9-4010-8144-63ce373586dd');



